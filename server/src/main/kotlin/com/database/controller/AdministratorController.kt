package com.database.controller

import com.database.model.Administrator
import com.database.model.AdministratorDAO
import com.database.model.CommodityDAO
import com.database.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class AdministratorController {
    @Autowired
    private lateinit var administratorDAO: AdministratorDAO

    //管理员账号只能手动添加
    @Autowired
    private lateinit var commodityDAO: CommodityDAO

    @Autowired
    private lateinit var adminService: AdminService

    //登录
    //http://domain.com/admin?serialNumber=123456789&password=123456
    @GetMapping("/admin")
    fun login(serialNumber: String, password: String): ResponseEntity<*> {
        val admin =
            adminService.checkAdmin(serialNumber, password) ?: return ResponseEntity("用户名或密码错误", HttpStatus.BAD_REQUEST)
        return ResponseEntity(admin, HttpStatus.OK)
    }

    @GetMapping("/admin/{serialNumber}")
    fun path(@PathVariable serialNumber: String, password: String): ResponseEntity<*> {
        val admin =
            adminService.checkAdmin(serialNumber, password) ?: return ResponseEntity("用户名或密码错误", HttpStatus.BAD_REQUEST)
        return ResponseEntity(admin, HttpStatus.OK)
    }

    //没有注册

    //显示个人的信息
    @GetMapping("/adminInfo")
    fun adminInfo(serialNumber: String): Administrator? {
        return administratorDAO.findBySerialNumber(serialNumber)
    }

    //修改个人信息，不包括密码
    @PostMapping("/modifyAdminInfo")
    fun modifyAdminInfo(@RequestBody admin: Administrator): ResponseEntity<HttpStatus> {
        administratorDAO.save(admin)
        return ResponseEntity(HttpStatus.OK)
    }

    //修改密码，需要确认两次
    @PostMapping("/modifyAdminPassword")
    fun modifyUserPassword(
        serialNumber: String, oldPassword: String, firstPassward: String, secondPassword: String
    ): ResponseEntity<*> {
        var admin = administratorDAO.findBySerialNumber(serialNumber);
        if (admin!!.password != oldPassword) {
            return ResponseEntity("用户旧密码输入不正确", HttpStatus.BAD_REQUEST)
        }
        if (firstPassward != secondPassword) {
            return ResponseEntity("两次输入的密码不一致", HttpStatus.BAD_REQUEST)
        }
        admin.password = firstPassward
        administratorDAO.save(admin)
        return ResponseEntity(admin, HttpStatus.OK)
    }
}