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

        appRepository.deleteAll()
        appRepository.save(listOf(
                App(ObjectId.get().toString(), "Facebook", "1.0.0", sampleDescription),
                App(ObjectId.get().toString(), "Twitter", "1.1.3", sampleDescription),
                App(ObjectId.get().toString(), "Asphalt", "2.1.3", sampleDescription) ,
                App("1", "App4", "3.1.3", sampleDescription),
                App("2", "App5", "4.1.3", sampleDescription)
        ))
    }

    private fun saveSampleUsersToDb() {
        userRepository.deleteAll()
        userRepository.save(listOf(
                User(ObjectId.get().toString(), "admin", "admin", appRepository.findAll()),
                User(ObjectId.get().toString(), "user2", "user2", appRepository.findAll()),
                User(ObjectId.get().toString(), "user3", "user3", emptyList())
        ))
    }
}