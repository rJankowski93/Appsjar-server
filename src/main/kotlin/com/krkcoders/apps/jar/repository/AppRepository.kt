package com.krkcoders.apps.jar.repository

import com.krkcoders.apps.jar.data.App
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AppRepository : MongoRepository<App, String> {

    fun findByName(name: String): App
}