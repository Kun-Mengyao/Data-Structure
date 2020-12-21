package com.database.model

import org.springframework.data.jpa.repository.JpaRepository

interface AdministratorDAO: JpaRepository<Administrator, String> {
    fun findBySerialNumber(serialNumber:String):Administrator

}