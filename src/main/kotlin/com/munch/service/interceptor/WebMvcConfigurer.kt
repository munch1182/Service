package com.munch.service.interceptor

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfigurer : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        registry.addInterceptor(HeaderInterceptor())
                .addPathPatterns("/*")
        registry.addInterceptor(TokenInterceptor())
                .addPathPatterns("/user/*")
    }

}