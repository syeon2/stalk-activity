package io.waterkite94.stalk.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application {
    fun run(vararg args: String) {
        SpringApplication.run(Application::class.java, *args)
    }
}
