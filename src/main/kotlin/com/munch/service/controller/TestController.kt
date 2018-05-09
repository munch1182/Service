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

    fun distance(): String {
        val dis = System.currentTimeMillis() - START
        val minutes = dis / 1000 / 60
        val hour = minutes / 60
        if (hour <= 0) {
            return minutes.toString().plus("分钟")
        }
        val day = hour / 24
        if (day <= 0) {
            return day.toString().plus("小时")
        }
        val year = day / 365
        if (year <= 0) {
            return day.toString().plus("天")
        }
        return year.toString().plus("年")
    }
}