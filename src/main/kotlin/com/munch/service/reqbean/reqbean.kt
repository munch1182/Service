package com.munch.service.reqbean

import com.munch.service.mysqlbean.ArticleContent
import com.munch.service.mysqlbean.ArticleTitle

data class ReqUserBean(val loginName: String, val pwd: String?, val save: Boolean)

data class ReqTokenBean(val token: String)

data class ReqArticleByIdBean(val artId: Long)

data class ReqNewArticleBean(val title: String, val content: String) {

    companion object {

        fun change2TitleBean(userId: Long, req: ReqNewArticleBean): ArticleTitle {
            return ArticleTitle(req.title, userId)
        }

        fun change2ContentBean(artId: Long, req: ReqNewArticleBean): ArticleContent {
            return ArticleContent(artId, req.content)
        }
    }
}

data class ReqModifyArticleBean(val artId: Long, val title: String, val content: String) {

    companion object {

        fun change2TitleBean(article: ArticleTitle, req: ReqModifyArticleBean): ArticleTitle {
            if (article.title != req.title) {
                article.title = req.title
            }
            return article
        }

        fun change2ContentBean(article: ArticleContent, req: ReqModifyArticleBean): ArticleContent {
            if (article.content != req.content) {
                article.content = req.content
            }
            return article
        }
    }
}