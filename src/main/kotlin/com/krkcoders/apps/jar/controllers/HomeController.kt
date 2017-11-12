package com.krkcoders.apps.jar.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    private val PAGE = "home"

    @GetMapping("/")
    fun home(): String {
        return PAGE
    }
}
