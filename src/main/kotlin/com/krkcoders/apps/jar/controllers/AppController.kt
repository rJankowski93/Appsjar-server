package com.krkcoders.apps.jar.controllers

import com.krkcoders.apps.jar.services.AppService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping


@Controller()
@RequestMapping("/app")
class AppController @Autowired constructor(private val appService: AppService) {

    private val PAGE = "AppDetails"

    @GetMapping("/{id}")
    fun getApp(model: Model, @PathVariable(value = "id") id: String): String {
        model.addAttribute("app", appService.getApp(id));
        return PAGE
    }
}