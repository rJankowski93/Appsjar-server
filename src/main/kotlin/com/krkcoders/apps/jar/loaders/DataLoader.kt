package com.krkcoders.apps.jar.loaders

import com.krkcoders.apps.jar.data.App
import com.krkcoders.apps.jar.repository.AppRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader
@Autowired constructor(
        private val appRepository: AppRepository
) : CommandLineRunner {


    override fun run(vararg args: String?) {

        val testApp1 = App(ObjectId.get().toString(), "FirstApp", "1.0.0")
        val testApp2 = App(ObjectId.get().toString(), "App2", "1.1.3")
        val testApp3 = App(ObjectId.get().toString(), "App3", "2.1.3")


        appRepository.deleteAll();
        appRepository.save(listOf(testApp1, testApp2, testApp3))
    }
}