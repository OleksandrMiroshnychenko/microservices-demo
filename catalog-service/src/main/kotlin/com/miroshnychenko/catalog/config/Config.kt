package com.miroshnychenko.catalog.config

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.miroshnychenko.catalog.dto.Product
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import java.io.File

@Configuration
class Config {

    @Value("\${alex.dot}")
    private lateinit var property: String

    @Bean
    fun data(): Flux<Product> {

        println(property)

        val bootstrapSchema: CsvSchema = CsvSchema.emptySchema().withHeader()
        val mapper: CsvMapper = CsvMapper()
        val file = File("/Users/omiroshnychenko/git/microservices-demo/data.csv")
        val readValues: List<Product> = mapper.readerFor(Product::class.java).with(bootstrapSchema).readValues<Product>(file).readAll()

        return Flux.fromIterable(readValues)
    }

    @Bean
    fun usersMicroserviceOpenAPI(): OpenAPI {
        return OpenAPI().info(
            Info().title("Miroshnychenko").description("catalog").version("0.0.1-SNAPSHOT")
        )
    }

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
            .build()
    }
}