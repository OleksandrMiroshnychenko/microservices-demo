package com.miroshnychenko.product.client

import com.miroshnychenko.product.dto.Product
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@ReactiveFeignClient(
    name = "catalog-service",
    url = "http://localhost:8082/catalog"
)
interface CatalogClient {

    @GetMapping("/{uniqId}")
    fun getProductByUniqId(@PathVariable("uniqId") uniqId: String): Mono<Product>

    @GetMapping("/sku/{sku}")
    fun getProductsBySku(@PathVariable sku: String): Flux<Product>
}