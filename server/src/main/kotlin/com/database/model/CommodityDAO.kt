package com.database.model

import org.springframework.data.jpa.repository.JpaRepository

interface CommodityDAO: JpaRepository<Commodity,Int> {
    fun findAllBySeller(seller:Int):List<Commodity>
}