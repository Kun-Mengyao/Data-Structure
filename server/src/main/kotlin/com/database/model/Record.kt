package com.database.model

import java.util.*
import javax.persistence.*

@Entity
class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = -1

    @Column
    var seller = -1

    @Column
    var buyer = -1

    @Column(columnDefinition = "datetime")
    var time = Date()
}