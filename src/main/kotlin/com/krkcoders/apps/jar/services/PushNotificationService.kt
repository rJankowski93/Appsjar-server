package com.krkcoders.apps.jar.services

import org.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

@Service
class PushNotificationService {

    @Value("\${firebase.server.key}")
    private var FIREBASE_SERVER_KEY: String = ""

    @Value("\${firebase.api.url}")
    private var FIREBASE_API_URL: String = ""

    fun sendMessage(topic: String, title: String, message: String): ResponseEntity<String?> {

        val body = JSONObject()
        body.put("to", "/topics/" + topic)
        body.put("priority", "high")

        val notification = JSONObject()
        notification.put("title", title)
        notification.put("body", message)

        body.put("notification", notification)

        val request = HttpEntity(body.toString())

        val pushNotification = this.send(request)

        CompletableFuture.allOf(pushNotification).join()

        try {
            val firebaseResponse = pushNotification?.get()
            return ResponseEntity(firebaseResponse, HttpStatus.OK)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }

        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @Async
    fun send(entity: HttpEntity<String>): CompletableFuture<String>? {
        val restTemplate = RestTemplate()

        restTemplate.interceptors = listOf(
                HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY),
                HeaderRequestInterceptor("Content-Type", "application/json")
        )

        val firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String::class.java)
        return CompletableFuture.completedFuture(firebaseResponse)
    }
}