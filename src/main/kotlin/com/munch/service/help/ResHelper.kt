package com.munch.service.help

open class ResHelper<T>(var status: Long = -1, var msg: String = "", var data: T? = null) {

    fun <U> changeType(func: (T) -> U?, status: Long, msg: String): ResHelper<U> {
        return ResHelper(status, msg, if (data != null) func(data!!) else null)
    }

    fun <U> changeType(func: (T) -> U?) = changeType(func, status, msg)
}

