package com.krkcoders.apps.jar.api

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/api/user")
class UserResources {

    @GetMapping(value = "/current")
    fun getCurrentUserName(): User {
        return SecurityContextHolder.getContext().authentication.principal as User
    }
}