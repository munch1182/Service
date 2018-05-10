package com.munch.service.controller

import com.munch.service.base.*
import com.munch.service.dao.UserDao
import com.munch.service.help.JwtHelper
import com.munch.service.help.JwtHelper.CODE_ERROR_EXPIRED
import com.munch.service.help.JwtHelper.CODE_SUCCESS
import com.munch.service.help.ResHelper
import com.munch.service.interceptor.TokenInterceptor
import com.munch.service.reqbean.ReqUserBean
import com.munch.service.resbean.ResTokenBean
import com.munch.service.resbean.ResUerBean
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

@RestController
class UserController {

    @Resource
    lateinit var dao: UserDao

    @RequestMapping("/login", method = [(RequestMethod.POST)])
    @ResponseBody
    fun loginByName(@RequestBody req: ReqUserBean): BaseResBean<ResUerBean> {

        val user = dao.findUserByLoginname(req.loginName) ?: return ResNotFoundDataBean("user")

        if (user.isBanned) {
            return ResAuthErrorBean("this user had bean baned")
        }
        user.logincount++

        dao.saveAndFlush(user)

        return ResSuccessBean(ResUerBean.change2Res(user, JwtHelper.compactToken(user.userId, req.save)))
    }


    @RequestMapping("/user/getuser", method = [(RequestMethod.POST)])
    @ResponseBody
    fun getUserByToken(@RequestHeader(name = TokenInterceptor.KEY_TOKEN) token: String): BaseResBean<ResUerBean> {
        val user = dao.findById(JwtHelper.User.getUserId(token))
        return if (user.isPresent) {
            ResSuccessBean(ResUerBean.change2Res(user.get(), token))
        } else {
            ResNotFoundDataBean("user")
        }
    }

    @RequestMapping("/user/refreshtoken", method = [(RequestMethod.POST)])
    @ResponseBody
    fun refreshToken(@RequestHeader(name = TokenInterceptor.KEY_TOKEN) token: String): BaseResBean<ResTokenBean> {
        return ResSuccessBean(ResTokenBean(JwtHelper.User.refreshToken(token)))
    }

    private fun <T, U> checkResFromJets(res: ResHelper<T>, func: (T) -> BaseResBean<U>): BaseResBean<U> {
        return when (res.status) {
            CODE_SUCCESS -> {
                if (res.data == null) {
                    ResUnknownBean()
                } else {
                    func(res.data!!)
                }
            }
            CODE_ERROR_EXPIRED -> ResNeedLoginBean()
            else -> ResUnknownBean()
        }
    }

}