package io.waterkite94.stalk.bootstrap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan("io.waterkite94.stalk")
@SpringBootApplication
class ActivityServiceApplication

fun main(args: Array<String>) {
    runApplication<ActivityServiceApplication>(*args)
}
