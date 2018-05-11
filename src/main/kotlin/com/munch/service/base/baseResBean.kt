package com.munch.service.base

object Status {
    const val SUCCESS = 200L
    const val NOT_FOUND = 404L
    const val AUTH_ERROR = 401L
    const val CON_NOT = 500L
    const val UNKNOWN = 999L
}

open class BaseResBean<out T>(val status: Long, val msg: String, val data: T?)

/** ---------------- SUCCESS ---------------- */

class ResSuccessBean<out T>(data: T? = null, msg: String = "success") : BaseResBean<T>(Status.SUCCESS, msg, data)

/** ---------------- NOT FOUND ---------------- */

class ResNotFoundBean<out T>(msg: String = "cannot find data", data: T? = null) : BaseResBean<T>(Status.NOT_FOUND, msg, data)

class ResNotFoundDataBean<out T>(type: String, data: T? = null) : BaseResBean<T>(Status.NOT_FOUND, "not find this $type", data)

/** ---------------- AUTH ERROR ---------------- */

open class ResAuthErrorBean<out T>(msg: String = "authority error", data: T? = null) : BaseResBean<T>(Status.AUTH_ERROR, msg, data)

class ResNeedLoginBean<out T>(msg: String = "need to login", data: T? = null) : ResAuthErrorBean<T>(msg, data)

/** ---------------- CANNOT ---------------- */

class ResCannotBean<out T>(msg: String = "service cannot do it now,please wait minutes", data: T? = null) : BaseResBean<T>(Status.CON_NOT, msg, data)

/** ---------------- UNKNOWN ---------------- */

class ResUnknownBean<out T>(msg: String = "what happen?", data: T? = null) : BaseResBean<T>(Status.UNKNOWN, msg, data)