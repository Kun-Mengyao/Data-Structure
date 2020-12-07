package com.database.model

import java.util.*
import javax.persistence.*

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = -1

    @Column(nullable = false, length = 50)
    var name = ""

    @Column(nullable = false, unique = true, length = 12)
    var serialNumber = ""

    @Lob
    @Column(nullable = false)
    var password = ""

    @Column(columnDefinition = "datetime")
    var birthday = Date()
}