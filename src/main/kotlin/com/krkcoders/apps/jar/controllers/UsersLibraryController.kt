package com.krkcoders.apps.jar.controllers

import com.krkcoders.apps.jar.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class UsersLibraryController @Autowired constructor(
        private val userRepository: UserRepository) {

    private val PAGE = "users-library"

    @GetMapping("/library")
    fun getUsersApps(model: Model): String {
        val currentUser = SecurityContextHolder.getContext().authentication.principal as User
        model.addAttribute("username", currentUser.username)
        model.addAttribute("usersApps", userRepository.findByUsername(currentUser.username).apps)
        return PAGE
    }
}