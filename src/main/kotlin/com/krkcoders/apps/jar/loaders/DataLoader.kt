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
                App(ObjectId.get().toString(), "Facebook", "1.0.0", "v=cNfK6LNuGa4", SampleDate.description, 3.0, SampleDate.facebookIcon),
                App(ObjectId.get().toString(), "Twitter", "1.1.3", "v=cNfK6LNuGa4", SampleDate.description, 3.0, SampleDate.twitterIcon),
                App(ObjectId.get().toString(), "Asphalt", "2.1.3", "v=cNfK6LNuGa4", SampleDate.description, 4.0, SampleDate.asphaltIcon),
                App("1", "EverNote", "3.1.3", "v=cNfK6LNuGa4", SampleDate.description, 5.0, SampleDate.evernoteIcon),
                App("2", "BallGame", "4.1.3", "v=cNfK6LNuGa4", SampleDate.description, 2.0, SampleDate.ballGameIcon)
        ))
    }

    private fun saveSampleUsersToDb() {
        userRepository.deleteAll()
        userRepository.save(listOf(
                User(ObjectId.get().toString(), "admin", "admin", listOf(appRepository.findByName("Facebook"), appRepository.findByName("Asphalt"), appRepository.findByName("App4"))),
                User(ObjectId.get().toString(), "user2", "user2", appRepository.findAll()),
                User(ObjectId.get().toString(), "user3", "user3", emptyList())
        ))
    }
}