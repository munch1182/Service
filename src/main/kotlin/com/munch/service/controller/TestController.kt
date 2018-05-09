package com.munch.service.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    companion object {
        private const val START = 1525876423748
    }

    @RequestMapping("/test")
    fun test(): String {
        val dis = distance()
        return "此时，已过去了约$dis。"
    }

    @RequestMapping("/user/test")
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