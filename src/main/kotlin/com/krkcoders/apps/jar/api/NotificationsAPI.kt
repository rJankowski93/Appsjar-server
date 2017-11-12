package com.krkcoders.apps.jar.api

import com.krkcoders.apps.jar.services.PushNotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = "/api/notification")
class NotificationsAPI @Autowired constructor(
        private val pushNotificationService: PushNotificationService) {

    @Value("\${firebase.global.topic}")
    private var globalTopic: String = ""

    @PostMapping(value = "/send/all", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun send(@RequestParam("username") username: String, @RequestParam("message") message: String): ResponseEntity<String?> {
        return pushNotificationService.sendMessage(globalTopic, username, message)
    }
}