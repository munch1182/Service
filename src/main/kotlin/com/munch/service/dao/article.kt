package com.munch.service.dao

import com.munch.service.mysqlbean.ArticleContent
import com.munch.service.mysqlbean.ArticleTitle
import com.munch.service.resbean.ResArticleBean
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ArticleTitleDao : JpaRepository<ArticleTitle, Long> {

    fun findAllByUserId(userId: Long): List<ArticleTitle>
}

interface ArticleContentDao : JpaRepository<ArticleContent, Long>

interface ArticleDao : JpaRepository<ResArticleBean, Long> {

    @Query("select t.art_id,t.title,u.name as author,c.content,t.createtime as create_time,c.modifytime as last_modify"
            + " from article_title t, article_content c,user u "
            + "where t.art_id = :artid  and c.art_id = :artid and u.user_id = t.user_id", nativeQuery = true)
    fun getContent(@Param("artid") artid: Long): ResArticleBean?
}