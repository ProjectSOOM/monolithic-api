package com.soom.monolithic_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@SpringBootApplication
@EnableRedisRepositories
class MonolithicApiApplication

fun main(args: Array<String>) {
    runApplication<MonolithicApiApplication>(*args)
}
