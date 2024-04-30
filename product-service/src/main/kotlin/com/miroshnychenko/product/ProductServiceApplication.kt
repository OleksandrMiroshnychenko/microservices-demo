package com.miroshnychenko.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactivefeign.spring.config.EnableReactiveFeignClients

@EnableReactiveFeignClients
@SpringBootApplication
class CatalogServiceApplication

fun main(args: Array<String>) {
	runApplication<CatalogServiceApplication>(*args)
}
