package com.soom.monolithic_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MonolithicApiApplication

fun main(args: Array<String>) {
    runApplication<MonolithicApiApplication>(*args)
}
