package com.database.controller

import com.database.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/user")
    fun login(serialNumber: String, password: String): ResponseEntity<*> {
        val user = userService.checkUser(serialNumber, password) ?: return ResponseEntity("用户名或密码错误",HttpStatus.BAD_REQUEST)

        return ResponseEntity(user, HttpStatus.OK)
    }
}