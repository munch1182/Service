package com.munch.service.help

import io.jsonwebtoken.*
import java.util.*

object JwtHelper {

    private const val KEY = "aasdlo112zx7waswep[d"
    private const val LONG_MILLIS = 30 * 24 * 60 * 60 * 1000L
    private const val EXPIRES_MILLIS = 15 * 60 * 1000L

    const val CODE_SUCCESS = 200L
    const val CODE_ERROR_SIGN = 201L
    const val CODE_ERROR_EXPIRED = 202L
    const val CODE_ERROR_ERROR = 203L

    private fun compactToken(sub: String, expires: Long): String {
        val nowMillis = System.currentTimeMillis()
        val date = Date(nowMillis)
        val builder = Jwts.builder()
                .setSubject(sub)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .setIssuedAt(date)//签发时间
        date.time = nowMillis + expires
        builder.setExpiration(date)//过期时间
//        builder.setNotBefore()//失效时间
        return builder.compact()
    }

    private fun validateJWT(jwt: String): ResHelper<Claims> {
        val result = ResHelper<Claims>()
        try {
            result.data = parseToken(jwt)
            result.status = CODE_SUCCESS
        } catch (e: SignatureException) {
            result.status = CODE_ERROR_SIGN
        } catch (e: ExpiredJwtException) {
            result.status = CODE_ERROR_EXPIRED
        } catch (e: Exception) {
            result.status = CODE_ERROR_ERROR
        }
        return result
    }

    private fun parseToken(jwt: String): Claims {
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(jwt)
                .body
    }

    fun compactToken(userId: Long, save: Boolean): String {
        return if (save) {
            compactToken(userId.toString(), LONG_MILLIS)
        } else {
            compactToken(userId.toString(), EXPIRES_MILLIS)
        }
    }

    /**
     * 在sub中保存userid
     */
    object User {
        fun getUserId(token: String): ResHelper<Long> {
            return validateJWT(token).changeType { clamis ->
                clamis.get(Claims.SUBJECT, String::class.java)?.toLong()
            }
        }

        fun refreshToken(token: String): ResHelper<String> {
            return getUserId(token).changeType { userid ->
                compactToken(userid.toString(), EXPIRES_MILLIS)
            }
        }
    }
}