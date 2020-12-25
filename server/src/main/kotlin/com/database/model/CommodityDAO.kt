package com.database.model

import org.springframework.data.jpa.repository.JpaRepository

interface CommodityDAO : JpaRepository<Commodity, Int> {
    fun findAllBySeller(seller: String): List<Commodity>
    fun findAllByBuyer(seller: String): List<Commodity>
    fun findAllByBuyerIsNot(empty: String): List<Commodity>
    fun findAllBySellerIsNot(empty: String): List<Commodity>
    fun findCommodityById(id: Int): Commodity
    fun existsCommodityById(id: Int): Boolean
    fun existsCommodityByIdAndSeller(id: Int, seller: String): Boolean
    fun findAllByStatus(status: CommodityStatus):List<Commodity>
}