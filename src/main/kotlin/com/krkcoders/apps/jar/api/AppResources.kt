package com.krkcoders.apps.jar.api

import com.krkcoders.apps.jar.data.App
import com.krkcoders.apps.jar.services.AppService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(value = "/api/app/")
class AppResources @Autowired constructor(private val appService: AppService) {

    private val APPS_PATH = "/apps/"

    @GetMapping(value = "/all")
    fun getAllApps(): List<App> {
        return appService.getAllApps()
    }

    @GetMapping(value = "/app/{id}")
    fun getApp(@PathVariable(value = "id") id: String): App {
        return appService.getApp(id)
    }

    @GetMapping("/file/{name}")
    @ResponseBody
    fun getAppFile(@PathVariable("name") name: String, request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<InputStreamResource>? {

        val file = ClassPathResource(APPS_PATH + name + ".apk")

        return ResponseEntity.ok().contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(InputStreamResource(file.inputStream))
    }
}