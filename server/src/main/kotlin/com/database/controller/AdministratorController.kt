package com.database.controller

import com.database.model.AdministratorDAO
import com.database.model.CommodityDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AdministratorController {
    @Autowired
    private lateinit var administratorDAO: AdministratorDAO
    //管理员账号只能手动添加
    @Autowired
    private lateinit var commodityDAO: CommodityDAO

    //登录
    @GetMapping("/admin")
    fun login(serialNumber:String,password:String){
        var admin=administratorDAO.
    }
}