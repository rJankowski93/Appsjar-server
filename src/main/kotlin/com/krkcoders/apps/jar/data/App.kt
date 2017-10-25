package com.krkcoders.apps.jar.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Apps")
data class App(
        @Id
        var id: String,
        var name: String,
        var version: String
)
