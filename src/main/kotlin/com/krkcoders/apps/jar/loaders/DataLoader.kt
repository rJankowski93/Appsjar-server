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
        val sampleDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus eget metus dapibus, molestie felis non, euismod neque. Vivamus sodales lobortis diam. Proin imperdiet ligula eget dapibus sollicitudin. Nunc sodales, sem ac ullamcorper bibendum, velit neque fermentum nibh, ac ultricies massa risus et diam. Morbi viverra, quam at vestibulum tristique, mi diam placerat est, eget molestie ligula odio eget sem. Vivamus sed tellus a sem consequat posuere eget in dolor. Sed posuere mauris at turpis vestibulum semper in ut arcu. Sed dui ante, lacinia eget mauris ac, varius gravida eros. Phasellus convallis metus nisl, non efficitur urna laoreet ut. Ut at maximus libero.\n" +
                "\n" +
                "                Cras at nulla sed magna tincidunt facilisis. Pellentesque a facilisis sapien. Cras mollis in diam molestie dapibus. Morbi a mauris velit. Etiam at odio quis diam interdum porta. Duis sed turpis sed quam dignissim feugiat ut quis arcu. Nullam vulputate diam risus, eget varius dui euismod vitae.\n" +
                "\n" +
                "                Nullam eleifend orci a augue varius, nec molestie metus mattis. Donec pharetra est sit amet magna egestas, a accumsan leo fermentum. Morbi nec purus nec elit feugiat sodales sed a nisi. Mauris consequat fringilla neque, eget bibendum turpis pellentesque in."

        val sampleImg = "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"

        appRepository.deleteAll()
        appRepository.save(listOf(
                App(ObjectId.get().toString(), "Facebook", "1.0.0", "v=cNfK6LNuGa4", sampleDescription, 3.0, sampleImg),
                App(ObjectId.get().toString(), "Twitter", "1.1.3", "v=cNfK6LNuGa4", sampleDescription, 3.0, sampleImg),
                App(ObjectId.get().toString(), "Asphalt", "2.1.3", "v=cNfK6LNuGa4", sampleDescription, 4.0, sampleImg),
                App("1", "App4", "3.1.3", "v=cNfK6LNuGa4", sampleDescription, 5.0, sampleImg),
                App("2", "App5", "4.1.3", "v=cNfK6LNuGa4", sampleDescription, 2.0, sampleImg)
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