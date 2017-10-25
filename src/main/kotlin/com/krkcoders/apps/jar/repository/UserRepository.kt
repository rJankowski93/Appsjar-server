package com.krkcoders.apps.jar.repository

import com.krkcoders.apps.jar.data.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {

    fun findByUsername(username: String): User
}