package com.krkcoders.apps.jar.controllers

import com.krkcoders.apps.jar.services.AppService
import com.krkcoders.apps.jar.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping


@Controller()
@RequestMapping("/app")
class AppController @Autowired constructor(private val appService: AppService,
                                           private val userService: UserService) {

    private val APP_DETAILS_PAGE = "AppDetails"
    private val ALL_APPS_PAGE = "Apps"

    @GetMapping("/{id}")
    fun getApp(model: Model, @PathVariable(value = "id") id: String): String {
        model.addAttribute("app", appService.getApp(id))
        return APP_DETAILS_PAGE
    }

    @GetMapping("/all")
    fun getAllAppsWithoutInstalled(model: Model): String {
        val currentUser = SecurityContextHolder.getContext().authentication.principal as User
        val currentUserApps = userService.getUserByUsername(currentUser.username).apps
        val apps = appService.getAllApps().filter { app -> !currentUserApps.contains(app) }

        model.addAttribute("apps", apps)

        return ALL_APPS_PAGE
    }
}