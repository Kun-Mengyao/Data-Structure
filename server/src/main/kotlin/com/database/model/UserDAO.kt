package com.database.model

import com.database.controller.Test
import com.database.controller.UserFormat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserDAO : JpaRepository<User, Int> {
    fun existsBySerialNumber(serialNumber: Int): Boolean
    fun findBySerialNumber(serialNumber: Int): User?


    fun save(userFormat: UserFormat){

    }

    fun findAllByBirthdayBetweenAndIdGreaterThanEqual(day1:Date,day2:Date,id:Int):List<User>

    @Query("select new com.database.controller.Test(u.id,u.name,u.password) from User u")
    fun getAllTests():MutableList<Test>
}