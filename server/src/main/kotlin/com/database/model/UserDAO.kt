package com.database.model

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserDAO: JpaRepository<User, String> {
    fun existsBySerialNumber(serialNumber: String): Boolean
    fun findBySerialNumber(serialNumber: String): User?




//    fun findAllByBirthdayBetweenAndIdGreaterThanEqual(day1:Date,day2:Date,id:Int):List<User>

//    @Query("select new com.database.controller.Test(u.id,u.name,u.password) from User u")
//    fun getAllTests():MutableList<Test>
}