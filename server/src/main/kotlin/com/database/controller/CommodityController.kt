package com.database.controller

import com.database.model.Commodity
import com.database.model.CommodityDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CommodityController {
    @Autowired
    private lateinit var commodityDAO: CommodityDAO

    //所有商品列表
    @GetMapping("/commodityList")
    fun commodityList():List<Commodity>{
        return commodityDAO.findAll()
    }

    //单个商品详细信息
    @GetMapping("/commodity")
    fun commodity(id:Int): Commodity {
        return commodityDAO.findById(id).get()
    }

    //上架
    @PostMapping("/sell")
    fun sell(@RequestBody commodity:Commodity): ResponseEntity<HttpStatus> {
        commodityDAO.save(commodity)
        return ResponseEntity(HttpStatus.OK)
    }

    //个人出售的商品
    @GetMapping("/sellOfUser")
    fun sellOfUser(serialNumber:String): List<Commodity> {
        return commodityDAO.findAllBySeller(serialNumber)
    }

    //个人购买的商品
    @GetMapping("/buyOfUser")
    fun buyOfUser(serialNumber:String): List<Commodity> {
        return commodityDAO.findAllByBuyer(serialNumber)
    }

    @GetMapping("/allTrade")
    fun allTrade(empty:String): List<Commodity> {
        return commodityDAO.findAllByBuyerIsNot(empty)
    }

}