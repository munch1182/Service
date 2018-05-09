package com.munch.service.dao

import com.munch.service.mysqlbean.ArticleContent
import com.munch.service.mysqlbean.ArticleTitle
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleTitleDao : JpaRepository<ArticleTitle, Long>

interface ArticleContentDao : JpaRepository<ArticleContent, Long>