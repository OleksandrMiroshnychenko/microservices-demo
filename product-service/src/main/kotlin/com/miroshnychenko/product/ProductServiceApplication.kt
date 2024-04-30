package com.miroshnychenko.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import reactivefeign.spring.config.EnableReactiveFeignClients

@EnableEurekaClient
@EnableReactiveFeignClients
@SpringBootApplication
class CatalogServiceApplication

fun main(args: Array<String>) {
	runApplication<CatalogServiceApplication>(*args)
}
