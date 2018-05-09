package com.munch.service.interceptor

import com.munch.service.help.JwtHelper
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenInterceptor : HandlerInterceptor {

    companion object {
        const val KEY_TOKEN = "X-Token"
        const val STATUS_ERROR = 999
        const val STATUS_EXPIRED = 888
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader(KEY_TOKEN)
        if (token == null || token.isEmpty()) {
            response.status = STATUS_ERROR
            return false
        }
        val res = JwtHelper.validateJWT(token)
        if (res.status == JwtHelper.CODE_ERROR_EXPIRED) {
            response.status = STATUS_EXPIRED
            return false
        } else if (res.status != JwtHelper.CODE_SUCCESS) {
            response.status = STATUS_ERROR
            return false
        }
        return true
    }
}