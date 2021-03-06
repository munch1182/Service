package com.munch.service.controller

import com.munch.service.base.BaseResBean
import com.munch.service.base.ResNotFoundDataBean
import com.munch.service.base.ResSuccessBean
import com.munch.service.base.ResUnknownBean
import com.munch.service.dao.*
import com.munch.service.help.JwtHelper
import com.munch.service.interceptor.TokenInterceptor
import com.munch.service.mysqlbean.ArticleContent
import com.munch.service.mysqlbean.ArticleTitle
import com.munch.service.reqbean.ReqArticleByIdBean
import com.munch.service.reqbean.ReqModifyArticleBean
import com.munch.service.reqbean.ReqNewArticleBean
import com.munch.service.resbean.ResArticleBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource
import javax.persistence.EntityManager


@RestController
class ArticleController {

    companion object {
        private const val BOXS_ID = 1000L
    }

    @Resource
    lateinit var titleDao: ArticleTitleDao
    @Resource
    lateinit var contentDao: ArticleContentDao
    @Resource
    lateinit var userDao: UserDao
    @Autowired
    lateinit var entityManager: EntityManager


    @RequestMapping("/user/boxs", method = [(RequestMethod.POST)])
    @ResponseBody
    fun getAllBoxs(@RequestHeader(name = TokenInterceptor.KEY_TOKEN) token: String): BaseResBean<List<ArticleTitle>> {
        val userOp = userDao.findById(JwtHelper.User.getUserId(token))
        if (!userOp.isPresent) {
            return ResUnknownBean("request with error userid")
        }
        return ResSuccessBean(titleDao.findAllByUserId(userOp.get().userId))
    }

    @RequestMapping("/user/content", method = [(RequestMethod.POST)])
    @ResponseBody
    fun getContent(@RequestBody req: ReqArticleByIdBean): BaseResBean<ResArticleBean> {
        val article = Article.getArticle(entityManager, req.artId) ?: return ResNotFoundDataBean("article")
        return ResSuccessBean(article)
    }

    @RequestMapping("/user/newarticle", method = [(RequestMethod.POST)])
    @ResponseBody
    fun newArticle(@RequestHeader(name = TokenInterceptor.KEY_TOKEN) token: String, @RequestBody req: ReqNewArticleBean): BaseResBean<ResArticleBean> {
        val userOp = userDao.findById(JwtHelper.User.getUserId(token))
        if (!userOp.isPresent) {
            return ResUnknownBean("request with error userid")
        }
        val title = titleDao.saveAndFlush(ReqNewArticleBean.change2TitleBean(userOp.get().userId, req))
        contentDao.saveAndFlush(ReqNewArticleBean.change2ContentBean(title.artId, req))
        return getContent(ReqArticleByIdBean(title.artId))
    }

    @RequestMapping("/user/modifyarticle", method = [(RequestMethod.POST)])
    @ResponseBody
    fun modifyArticle(@RequestBody req: ReqModifyArticleBean): BaseResBean<ResArticleBean> {
        val articleOp = titleDao.findById(req.artId)
        if (!articleOp.isPresent) {
            return ResUnknownBean("request with error artid")
        }
        titleDao.saveAndFlush(ReqModifyArticleBean.change2TitleBean(articleOp.get(), req))
        val contentOp = contentDao.findById(req.artId)
        val content: ArticleContent
        if (!contentOp.isPresent) {
            content = ArticleContent()
            content.artId = req.artId
        } else {
            content = contentOp.get()
        }

        contentDao.saveAndFlush(ReqModifyArticleBean.change2ContentBean(content, req))
        return getContent(ReqArticleByIdBean(req.artId))
    }

    @RequestMapping("/user/deletearticle", method = [(RequestMethod.POST)])
    @ResponseBody
    fun deleteArticle(@RequestHeader(name = TokenInterceptor.KEY_TOKEN) token: String, @RequestBody req: ReqArticleByIdBean): BaseResBean<Int> {
        val userOp = userDao.findById(JwtHelper.User.getUserId(token))
        if (!userOp.isPresent) {
            return ResUnknownBean("request with error userid")
        }
        val titleOp = titleDao.findById(req.artId)
        if (titleOp.isPresent) {
            titleDao.deleteById(req.artId)
        }
        val contentOp = contentDao.findById(req.artId)
        if (contentOp.isPresent) {
            contentDao.deleteById(req.artId)
        }
        return ResSuccessBean()
    }

    @RequestMapping("/boxs", method = [(RequestMethod.GET)])
    fun getBoxs(): BaseResBean<ResArticleBean> {
        return getContent(ReqArticleByIdBean(BOXS_ID))
    }

    @RequestMapping("/{artid}/content", method = [(RequestMethod.GET)])
    fun getContentByGet(@PathVariable(name = "artid") artid: Long): String {
        return getContent(ReqArticleByIdBean(artid)).data?.content ?: "empty"
    }
}