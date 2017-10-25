package com.krkcoders.apps.jar.loaders

import com.krkcoders.apps.jar.data.App
import com.krkcoders.apps.jar.data.User
import com.krkcoders.apps.jar.repository.AppRepository
import com.krkcoders.apps.jar.repository.UserRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader @Autowired constructor(private val appRepository: AppRepository,
                                        private val userRepository: UserRepository) : CommandLineRunner {


    override fun run(vararg args: String?) {
        saveSampleAppsToDb()
        saveSampleUsersToDb()
    }

    private fun saveSampleAppsToDb() {
        appRepository.deleteAll()
        appRepository.save(listOf(
                App(ObjectId.get().toString(), "FirstApp", "1.0.0"),
                App(ObjectId.get().toString(), "App2", "1.1.3"),
                App(ObjectId.get().toString(), "App3", "2.1.3")
        ))
    }

    private fun saveSampleUsersToDb() {
        userRepository.deleteAll()
        userRepository.save(listOf(
                User(ObjectId.get().toString(), "user1", "user1", appRepository.findAll()),
                User(ObjectId.get().toString(), "user2", "user2", appRepository.findAll()),
                User(ObjectId.get().toString(), "user3", "user3", emptyList())
        ))
    }
}