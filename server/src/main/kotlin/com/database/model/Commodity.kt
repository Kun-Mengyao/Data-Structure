package com.database.model

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

enum class CommodityStatus {
    OnSale, Ordered, InTransit, Finished
}

@Entity
class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = -1

    @Column(nullable = false, length = 50)
    var name = ""

    @Column(nullable = false, scale = 2, length = 8)
    var price = BigDecimal.ZERO

    @Column
    var picture = "https://pic-bed.xyz/res/userFiles/MyKun666/120.bmp"

    @Column(nullable = false)
    var description = ""


    @Column(nullable = false)
    var seller = ""

    @Column
    var buyer = ""

    @Column
    var status = CommodityStatus.OnSale

    //Time
    @Column(columnDefinition = "datetime")
    var time = Date()
}