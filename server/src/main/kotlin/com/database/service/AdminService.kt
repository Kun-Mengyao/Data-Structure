package com.database.service

import com.database.model.Administrator
import com.database.model.AdministratorDAO
import com.database.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdminService {
    @Autowired
    private lateinit var administratorDAO: AdministratorDAO

    fun checkAdmin(serialNumber: String, password: String): Administrator? {
        return if (!administratorDAO.existsBySerialNumber(serialNumber))
            null
        else {
            val admin = administratorDAO.findBySerialNumber(serialNumber)
            if (admin.password == password)
                admin
            else
                null
        }
    }
}