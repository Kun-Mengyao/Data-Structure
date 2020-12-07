package com.database.service

import com.database.model.User
import com.database.model.UserDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    private lateinit var userDAO: UserDAO

    fun checkUser(serialNumber: String, password: String): User? {
        return if(!userDAO.existsBySerialNumber(serialNumber))
            null
        else {
            val user = userDAO.findBySerialNumber(serialNumber)
            if(user!!.password == password)
                user
            else
                null
        }
    }

    fun String.ziHuaNB(): String {
        return "$this ziHuaNB"
    }

    fun call() {
        "12345".ziHuaNB()
    }
}