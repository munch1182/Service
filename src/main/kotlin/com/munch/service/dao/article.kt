package com.munch.service.dao

import com.munch.service.mysqlbean.*
import com.munch.service.resbean.ResArticleBean
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.EntityManager

interface ArticleTitleDao : JpaRepository<ArticleTitle, Long> {

    fun findAllByUserId(userId: Long): List<ArticleTitle>
}

interface ArticleContentDao : JpaRepository<ArticleContent, Long>

object Article {

    fun getArticle(entryManager: EntityManager, artId: Long): ResArticleBean? {
        val title = QArticleTitle.articleTitle
        val content = QArticleContent.articleContent
        val user = QUser.user
        return JPAQueryFactory(entryManager).select(
                Projections.bean(ResArticleBean::class.java,
                        title.artId,
                        title.title,
                        user.name.`as`("author"),
                        content.content,
                        title.createtime.`as`("createTime"),
                        content.modifytime.`as`("lastModify")))
                .from(title, content, user)
                .where(title.artId.eq(artId).and(content.artId.eq(artId)).and(title.userId.eq(user.userId)))
                .fetchOne()
    }

}