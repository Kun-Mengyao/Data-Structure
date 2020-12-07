package com.database.model

import org.springframework.data.jpa.repository.JpaRepository

interface UserDAO : JpaRepository<User, Int> {
    fun existsBySerialNumber(serialNumber: String): Boolean
    fun findBySerialNumber(serialNumber: String): User?
}