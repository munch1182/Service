package com.munch.service.resbean

import com.munch.service.mysqlbean.User
import java.sql.Timestamp

data class ResUerBean(val id: Long, val name: String, val isAdmin: Boolean, val regTime: Timestamp, val count: Long, val token: String) {

    companion object {

        fun change2Res(user: User, token: String): ResUerBean {
            return ResUerBean(user.userId, user.name, user.isAdmin, user.regtime, user.logincount, token)
        }
    }
}

data class ResTokenBean(val token: String)