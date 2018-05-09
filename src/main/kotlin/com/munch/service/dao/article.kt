package com.munch.service.dao

import com.munch.service.mysqlbean.ArticleContent
import com.munch.service.mysqlbean.ArticleTitle
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleTitleDao : JpaRepository<ArticleTitle, Long>{

    fun findAllByUserId(userId:Long):List<ArticleTitle>
}

interface ArticleContentDao : JpaRepository<ArticleContent, Long>