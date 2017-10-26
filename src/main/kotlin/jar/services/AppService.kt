package com.krkcoders.apps.jar.services

import com.krkcoders.apps.jar.data.App
import com.krkcoders.apps.jar.repository.AppRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppService @Autowired constructor(private val appRepository: AppRepository) {

    fun getAllApps(): List<App> {
        return appRepository.findAll()
    }
}