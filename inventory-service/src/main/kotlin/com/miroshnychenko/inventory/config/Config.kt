package com.miroshnychenko.inventory.config

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvParser
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.miroshnychenko.inventory.dto.AvailabilityStatus
import com.miroshnychenko.inventory.dto.ProductAvailability
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
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

    @Bean
    fun data(): Flux<ProductAvailability> {

        val bootstrapSchema: CsvSchema = CsvSchema.builder().addColumn("uniqId").build().withHeader()
        val mapper = CsvMapper()
        val file = File("/Users/omiroshnychenko/git/microservices-demo/data.csv")
        val readValues: List<ProductAvailability> =
            mapper.enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE).readerFor(ProductAvailability::class.java)
                .with(bootstrapSchema).readValues<ProductAvailability?>(file).readAll()

        return Flux.fromIterable(readValues).map {
            it.availability = AvailabilityStatus.entries.toTypedArray().random()
            it
        }
    }

    @Bean
    fun usersMicroserviceOpenAPI(): OpenAPI {
        return OpenAPI().info(
            Info().title("Miroshnychenko").description("inventory").version("0.0.1-SNAPSHOT")
        )
    }

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java)).build()
    }
}