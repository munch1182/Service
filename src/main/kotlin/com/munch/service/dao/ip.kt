package com.munch.service.dao

import com.munch.service.mysqlbean.Ip
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface IPDao : JpaRepository<Ip, Long> {

    @Query("select ip from ip where ip = ?1 and auth = 1",nativeQuery = true)
    fun findIPbyIp(ip: String): String?
}