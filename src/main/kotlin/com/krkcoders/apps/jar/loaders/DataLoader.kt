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
                App(ObjectId.get().toString(), "Facebook", "1.0.0", "cNfK6LNuGa4", SampleDate.descriptions.description1, 3.0, SampleDate.icons.facebookIcon),
                App(ObjectId.get().toString(), "Twitter", "1.1.3", "v=cNfK6LNuGa4", SampleDate.descriptions.description1, 3.0, SampleDate.icons.twitterIcon),
                App(ObjectId.get().toString(), "TicTacToe", "2.5.3", "v=cNfK6LNuGa4", SampleDate.descriptions.description1, 3.0, SampleDate.icons.ticTacToeIcon),
                App(ObjectId.get().toString(), "Calculator", "1.2.1", "v=cNfK6LNuGa4", SampleDate.descriptions.description1, 3.0, SampleDate.icons.calculatorLogo),
                App(ObjectId.get().toString(), "Asphalt", "2.1.3", "v=cNfK6LNuGa4", SampleDate.descriptions.description1, 4.0, SampleDate.icons.asphaltIcon),
                App("1", "EverNote", "3.1.3", "v=cNfK6LNuGa4", SampleDate.descriptions.description1, 5.0, SampleDate.icons.evernoteIcon),
                App("2", "BallGame", "4.1.3", "v=cNfK6LNuGa4", SampleDate.descriptions.description1, 2.0, SampleDate.icons.ballGameIcon)
        ))
    }

    private fun saveSampleUsersToDb() {
        userRepository.deleteAll()
        userRepository.save(listOf(
                User(ObjectId.get().toString(), "admin", "admin", listOf(appRepository.findByName("Facebook"), appRepository.findByName("Asphalt"), appRepository.findByName("BallGame"), appRepository.findByName("Calculator"))),
                User(ObjectId.get().toString(), "user2", "user2", appRepository.findAll()),
                User(ObjectId.get().toString(), "user3", "user3", emptyList())
        ))
    }
}