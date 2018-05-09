package com.munch.service.controller

import com.munch.service.dao.ArticleContentDao
import com.munch.service.dao.ArticleTitleDao
import com.munch.service.interceptor.TokenInterceptor
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
class ArticleController {

    companion object {
        private const val BOXS_ID = 1000L
    }

    @Resource
    lateinit var titleDao: ArticleTitleDao
    @Resource
    lateinit var contentDao: ArticleContentDao

    @RequestMapping("/user/boxs")
    fun getAllBoxs(@RequestHeader(name = TokenInterceptor.KEY_TOKEN) token: String) {
        val boxs = contentDao.findById(BOXS_ID).get()
    }
}