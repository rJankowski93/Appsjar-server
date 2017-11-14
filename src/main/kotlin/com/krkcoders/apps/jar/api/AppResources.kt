package com.krkcoders.apps.jar.api

import com.krkcoders.apps.jar.data.App
import com.krkcoders.apps.jar.services.AppService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = "/api/app/")
class AppResources @Autowired constructor(private val appService: AppService) {

    @GetMapping(value = "/all")
    fun getAllApps(): List<App> {
        return appService.getAllApps()
    }

    @GetMapping(value = "/app/{id}")
    fun getApp(@PathVariable(value = "id") id: String): App {
        return appService.getApp(id);
    }
}