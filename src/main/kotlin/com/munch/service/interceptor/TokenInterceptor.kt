package com.munch.service.interceptor

import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenInterceptor : HandlerInterceptor {

    companion object {
        const val KEY_TOKEN = "X-Token"
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (request.getHeader(KEY_TOKEN).isEmpty()) {
            return false
        }
        return true
    }
}