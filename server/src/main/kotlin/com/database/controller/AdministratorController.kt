package com.database.controller

import com.database.model.AdministratorDAO
import com.database.model.CommodityDAO
import com.database.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

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
    @GetMapping("/admin")
    fun admin(serialNumber: String, password: String): ResponseEntity<*> {
        val admin =
            adminService.checkAdmin(serialNumber, password) ?: return ResponseEntity("用户名或密码错误", HttpStatus.BAD_REQUEST)
        return ResponseEntity(admin, HttpStatus.OK)
    }
}