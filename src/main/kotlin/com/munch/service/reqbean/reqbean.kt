package com.munch.service.reqbean

data class ReqUserBean(val name: String, val pwd: String?, val save: Boolean)

data class ReqTokenBean(val token: String)