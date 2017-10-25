package com.krkcoders.apps.jar.api

import com.krkcoders.apps.jar.data.App
import com.krkcoders.apps.jar.repository.AppRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/api/user")
open class UserResources
@Autowired
constructor(val appRepository: AppRepository){

    @GetMapping(value = "/current")
    fun getCurrentUserName(): User {
        return SecurityContextHolder.getContext().authentication.principal as User
    }

    @GetMapping(value = "/create")
    fun createUser() : List<App>{
        return appRepository.findAll()
    }
}