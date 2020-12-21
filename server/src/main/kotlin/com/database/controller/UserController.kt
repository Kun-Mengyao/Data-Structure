package com.database.controller

import com.database.model.Commodity
import com.database.model.CommodityDAO
import com.database.model.UserDAO
import com.database.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.stereotype.Controller

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
    fun login(serialNumber: Int, password: String): ResponseEntity<*> {
        val user = userService.checkUser(serialNumber, password) ?: return ResponseEntity("用户名或密码错误",HttpStatus.BAD_REQUEST)
        return ResponseEntity(user, HttpStatus.OK)
    }

    //http://domain.com/user/123456789?password=123456
    @GetMapping("/user/{serialNumber}")
    fun path(@PathVariable serialNumber: Int, password: String): ResponseEntity<*> {
        val user = userService.checkUser(serialNumber, password) ?: return ResponseEntity("用户名或密码错误",HttpStatus.BAD_REQUEST)
        return ResponseEntity(user, HttpStatus.OK)
    }

    //http://localhost:10086/api/test
    @GetMapping("/test")
    fun body(@RequestBody test: Test): ResponseEntity<*> {
        val (a,_,c)=test
        val user = userService.checkUser(a, c) ?: return ResponseEntity("用户名或密码错误",HttpStatus.BAD_REQUEST)
        return ResponseEntity(user, HttpStatus.OK)
    }

    //注册
    @GetMapping("/register")
    fun register(@RequestBody user:UserFormat):ResponseEntity<HttpStatus>{
        if(userDAO.existsBySerialNumber(user.serialNumber))
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        else
            return ResponseEntity(HttpStatus.OK)
    }

    //上架
    @GetMapping("/sell")
    fun sell(@RequestBody commodity:Commodity):ResponseEntity<HttpStatus>{
//        var commo=Commodity()
//        commo.name = commodity.name
//        commo.price = commodity.price
//        commo.picture = commodity.picture
//        commo.description = commodity.description
//        commo.seller = commodity.seller

        commodityDAO.save(commodity)
        return ResponseEntity(HttpStatus.OK)
    }

    //商品列表
    @GetMapping("/commodityList")
    fun commodityList():List<Commodity>{
        return commodityDAO.findAll()
    }
}