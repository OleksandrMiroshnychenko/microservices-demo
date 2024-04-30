package com.miroshnychenko.product.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RestController
import reactivefeign.spring.config.EnableReactiveFeignClients
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class Config {

    @Bean
    fun usersMicroserviceOpenAPI(): OpenAPI {
        return OpenAPI().info(
            Info().title("Miroshnychenko").description("product").version("0.0.1-SNAPSHOT")
        )
    }

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java)).build()
    }
}