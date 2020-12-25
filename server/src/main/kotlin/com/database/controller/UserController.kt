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

@CrossOrigin
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
    fun modifyUserInfo(@RequestBody user: User): ResponseEntity<User> {
        //user.id = userDAO.findBySerialNumber(user.serialNumber)?.id!!
        return ResponseEntity(userDAO.save(user), HttpStatus.OK)
    }

    //修改密码,需要确认两次
    @PostMapping("/modifyUserPassword")
    fun modifyUserPassword(
        serialNumber: String, newPassword: String
    ): ResponseEntity<*> {
        val user = userDAO.findBySerialNumber(serialNumber);

        user!!.password = newPassword
        userDAO.save(user)
        return ResponseEntity(user, HttpStatus.OK)
    }

}