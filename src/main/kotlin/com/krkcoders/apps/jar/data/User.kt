package com.krkcoders.apps.jar.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Users")
data class User(
        @Id
        val id:String,
        val username:String,
        val password:String,
        val apps:List<App>
)