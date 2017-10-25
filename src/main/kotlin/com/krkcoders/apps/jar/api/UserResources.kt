package com.krkcoders.apps.jar.api

import com.krkcoders.apps.jar.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/api/user")
class UserResources @Autowired constructor(private val userService: UserService) {

    @GetMapping(value = "/current")
    fun getCurrentUserName(): User {
        return SecurityContextHolder.getContext().authentication.principal as User
    }

    @GetMapping()
    fun getUserByUsername(@RequestParam("username") username: String): com.krkcoders.apps.jar.data.User {
        return userService.getUserByUsername(username)
    }
}