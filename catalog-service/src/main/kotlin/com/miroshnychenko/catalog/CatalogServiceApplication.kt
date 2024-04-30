package com.miroshnychenko.catalog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class CatalogServiceApplication

fun main(args: Array<String>) {
	runApplication<CatalogServiceApplication>(*args)
}
