package com.database.controller

import com.database.model.Commodity
import com.database.model.CommodityDAO
import com.database.model.User
import com.database.model.UserDAO
import com.database.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userDAO: UserDAO

    @Autowired
    private lateinit var commodityDAO: CommodityDAO

    //登录
    //http://domain.com/user?serialNumber=123456789&password=123456
    @GetMapping("/user")
    fun login(serialNumber: String, password: String): ResponseEntity<*> {
        val user =
            userService.checkUser(serialNumber, password) ?: return ResponseEntity("用户名或密码错误", HttpStatus.BAD_REQUEST)
        return ResponseEntity(user, HttpStatus.OK)
    }

    //http://domain.com/user/123456789?password=123456
    @GetMapping("/user/{serialNumber}")
    fun path(@PathVariable serialNumber: String, password: String): ResponseEntity<*> {
        val user =
            userService.checkUser(serialNumber, password) ?: return ResponseEntity("用户名或密码错误", HttpStatus.BAD_REQUEST)
        return ResponseEntity(user, HttpStatus.OK)
    }

    //http://localhost:10086/api/test
//    @GetMapping("/test")
//    fun body(@RequestBody test: Test): ResponseEntity<*> {
//        val (a,_,c)=test
//        val user = userService.checkUser(a, c) ?: return ResponseEntity("用户名或密码错误",HttpStatus.BAD_REQUEST)
//        return ResponseEntity(user, HttpStatus.OK)
//    }

    //注册
    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<HttpStatus> {
        if (userDAO.existsBySerialNumber(user.serialNumber))
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        else
            userDAO.save(user)
        return ResponseEntity(HttpStatus.OK)
    }

    //显示个人信息
    @GetMapping("/userInfo")
    fun userInfo(serialNumber: String): User? {
        return userDAO.findBySerialNumber(serialNumber)
    }

    //修改个人信息,不包括修改密码
    @PostMapping("/modifyUserInfo")
    fun modifyUserInfo(@RequestBody user: User): ResponseEntity<HttpStatus> {
        //user.id = userDAO.findBySerialNumber(user.serialNumber)?.id!!

        userDAO.save(user)

        return ResponseEntity(HttpStatus.OK)
    }

    //修改密码,需要确认两次
    @PostMapping("/modifyUserPassword")
    fun modifyUserPassword(
        @PathVariable serialNumber: String, oldPassword: String, firstPassward: String, secondPassword: String
    ): ResponseEntity<*> {
        var user = userDAO.findBySerialNumber(serialNumber);
        if (user == null) {

        }
        if (user!!.password != oldPassword) {
            return ResponseEntity("用户旧密码输入不正确", HttpStatus.BAD_REQUEST)
        }
        if (firstPassward != secondPassword) {
            return ResponseEntity("两次输入的密码不一致", HttpStatus.BAD_REQUEST)
        }
        user.password = firstPassward
        userDAO.save(user)
        return ResponseEntity(user, HttpStatus.OK)
    }

}