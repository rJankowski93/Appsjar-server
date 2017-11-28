package com.krkcoders.apps.jar.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Apps")
data class App(
        @Id
        val id: String,
        val name: String,
        val version: String,
        val youTubeId: String,
        val description: String,
        val rating: Double,
        val img: String
)
