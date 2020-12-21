package com.database.controller

import com.database.model.AdministratorDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

@RestController
class AdministratorController {
    @Autowired
    private lateinit var administratorDAO:AdministratorDAO

}