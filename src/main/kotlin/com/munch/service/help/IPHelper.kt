package com.munch.service.help

import javax.servlet.http.HttpServletRequest

object IPHelper {

    fun getIp(request: HttpServletRequest): String {
        var ip = request.getHeader("X-Real-IP")
        if (isTrue(ip)) {
            return ip
        }
        ip = request.getHeader("X-Forwarded-For")
        if (isTrue(ip)) {
            return ip // 真实ip是第一个，后续为代理ip
        }
        return request.remoteAddr
    }

    private fun isTrue(ip: String?) = !ip.isNullOrBlank() && "unknown" != ip
}