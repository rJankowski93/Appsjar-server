package com.krkcoders.apps.jar.services

import com.krkcoders.apps.jar.data.User
import com.krkcoders.apps.jar.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(private val userRepository: UserRepository) {

    fun getUserByUsername(userName: String): User {
        return userRepository.findByUsername(userName)
    }
}