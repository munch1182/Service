package com.munch.service.dao

import com.munch.service.mysqlbean.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserDao : JpaRepository<User, Long> {

    fun findUserByName(name: String): User?
}