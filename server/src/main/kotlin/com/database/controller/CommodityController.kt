package com.database.controller

import com.database.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.management.openmbean.CompositeDataInvocationHandler
import javax.net.ssl.HttpsURLConnection

@RestController
class CommodityController {
    @Autowired
    private lateinit var commodityDAO: CommodityDAO

    @Autowired
    private lateinit var userDAO: UserDAO

    @Autowired
    private lateinit var administratorDAO: AdministratorDAO

    //所有商品列表
    @GetMapping("/commodityList")
    fun commodityList(): List<Commodity> {
        return commodityDAO.findAll()
    }

    //单个商品详细信息
    @GetMapping("/commodity")
    fun commodity(id: Int): Commodity {
        return commodityDAO.findById(id).get()
    }

    //个人出售的商品
    @GetMapping("/sellOfUser")
    fun sellOfUser(serialNumber: String): List<Commodity> {
        return commodityDAO.findAllBySeller(serialNumber)
    }

    //个人购买的商品
    @GetMapping("/buyOfUser")
    fun buyOfUser(serialNumber: String): List<Commodity> {
        return commodityDAO.findAllByBuyer(serialNumber)
    }

    //获取交易记录(买家不为""，但不是null)
    @GetMapping("/allTrade")
    fun allBuyerTrade(): List<Commodity> {
        return commodityDAO.findAllByBuyerIsNot("")
    }

    ///改变商品的状态 商品的初始状态为onSale
    //上架onSale
    @PostMapping("/sell")
    fun sell(@RequestBody commodity: Commodity): ResponseEntity<HttpStatus> {
        commodityDAO.save(commodity)
        return ResponseEntity(HttpStatus.OK)
    }

    //下单一个商品ordered
    @PostMapping("/order")
    fun order(id: Int, buyer: String): ResponseEntity<*> {
        if (!commodityDAO.existsCommodityById(id)) {
            return ResponseEntity("没有找到对应的商品", HttpStatus.BAD_REQUEST)
        }
        if (!userDAO.existsBySerialNumber(buyer)) {
            return ResponseEntity("买家不存在", HttpStatus.BAD_REQUEST)
        }
        var commodity = commodityDAO.findCommodityById(id)
        commodity.buyer = buyer
        commodity.status = CommodityStatus.在售中
        commodityDAO.save(commodity)
        return ResponseEntity("下单成功", HttpStatus.OK)
    }

    //确认一个商品正在运输
    @PostMapping("/inTransit")
    fun transit(id: Int): ResponseEntity<*> {
        if (!commodityDAO.existsCommodityById(id)) {
            return ResponseEntity("没有找到对应的商品", HttpStatus.BAD_REQUEST)
        }
        var commodity = commodityDAO.findCommodityById(id)
        commodity.status = CommodityStatus.运输中
        commodityDAO.save(commodity)
        return ResponseEntity("确认运输成功", HttpStatus.OK)
    }

    //确认一个商品订单完成
    @PostMapping("/finished")
    fun finish(id: Int): ResponseEntity<*> {
        if (!commodityDAO.existsCommodityById(id)) {
            return ResponseEntity("没有找到对应的商品", HttpStatus.BAD_REQUEST)
        }
        var commodity = commodityDAO.findCommodityById(id)
        commodity.status = CommodityStatus.已完成
        commodityDAO.save(commodity)
        return ResponseEntity("订单已完成", HttpStatus.OK)
    }

    //管理员下架商品
    @PostMapping("/adminUnSell")
    fun adminUnSell(id: Int): HttpStatus {
        if (commodityDAO.existsById(id)) {
            commodityDAO.deleteById(id)
            return HttpStatus.OK
        } else
            return HttpStatus.BAD_REQUEST
    }

    //用户下架商品
    @PostMapping("/userUnSell")
    fun userUnSell(id: Int, serialNumber: String): ResponseEntity<*> {
        if (!commodityDAO.existsCommodityByIdAndSeller(id, serialNumber)) {
            return ResponseEntity("您没有在售这个商品", HttpStatus.BAD_REQUEST)
        }
        commodityDAO.deleteById(id);
        return ResponseEntity("下架成功", HttpStatus.OK)
    }
}