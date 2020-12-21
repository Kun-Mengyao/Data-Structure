package com.database.model

import javax.persistence.*

@Entity
class Administrator {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    var id = -1
//
//    @Column(nullable = false, unique = true, length = 12)
    @Id
    var serialNumber = ""

    @Column(nullable = false, length = 50)
    var name = ""

    @Column(nullable = false)
    var sex = ""

    @Lob
    @Column(nullable = false)
    var password = ""

}