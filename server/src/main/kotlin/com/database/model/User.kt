package com.database.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
class User {
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

    @Column(nullable = false)
    var college = ""

    @Column(nullable = false)
    var major = ""

    @Column(nullable = false)
    var dormitory = ""

    @Lob
    @Column(nullable = false)
    @JsonIgnore
    var password = ""

    @Column(columnDefinition = "datetime")
    var birthday = Date()
}