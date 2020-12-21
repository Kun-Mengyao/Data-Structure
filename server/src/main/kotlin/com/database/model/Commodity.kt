package com.database.model

import java.util.*
import javax.persistence.*

@Entity
class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = -1

    @Column(nullable = false, length = 50)
    var name = ""

    @Column(nullable = false)
    var price = 0.0

    @Column
    var picture = "https://pic-bed.xyz/res/userFiles/MyKun666/120.bmp"

    @Column(nullable = false)
    var description = ""


    @Column(nullable = false)
    var seller = ""

    @Column
    var buyer = ""
}