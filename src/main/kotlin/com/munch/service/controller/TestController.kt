package com.munch.service.controller

import com.munch.service.help.IPHelper
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class TestController {

    companion object {
        private const val START = 1525876423748
    }

    @RequestMapping("/test", method = [(RequestMethod.GET)])
    fun test(res: HttpServletResponse, req: HttpServletRequest): String {
        val dis = distance()
        res.addHeader("IP", IPHelper.getIp(req))
        return "此时，已过去了约$dis。"
    }

    @RequestMapping("/ip", method = [(RequestMethod.GET)])
    fun ip(req: HttpServletRequest): String {
        return IPHelper.getIp(req)
    }

    @RequestMapping("/user/test", method = [(RequestMethod.GET)])
    fun test2(): String {
        val dis = distance()
        return "此时，已过去了约$dis。"
    }

    private fun distance(): String {
        val dis = System.currentTimeMillis() - START
        val minutes = dis / 1000 / 60
        return if (minutes < 60) {
            minutes.toString().plus("分钟")
        } else {
            val hours = minutes / 60
            if (hours < 24) {
                hours.toString().plus("小时")
            } else {
                val days = hours / 24
                if (days < 365) {
                    days.toString().plus("天")
                } else {
                    val year = days / 365
                    year.toString().plus("年")
                }
            }
        }
    }
}