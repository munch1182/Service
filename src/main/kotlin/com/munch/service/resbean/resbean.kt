package com.munch.service.resbean

import com.munch.service.mysqlbean.User
import java.sql.Timestamp

data class ResUerBean(val id: Long, val name: String, val isAdmin: Boolean, val regTime: Timestamp, val count: Long) {

    companion object {

        fun change2Res(user: User): ResUerBean {
            return ResUerBean(user.userId, user.name, user.isAdmin, user.regtime, user.logincount)
        }
    }
}

data class ResTokenBean(val token: String)