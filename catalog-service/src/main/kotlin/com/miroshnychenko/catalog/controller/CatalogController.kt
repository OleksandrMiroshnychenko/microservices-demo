package com.miroshnychenko.catalog.controller

import com.miroshnychenko.catalog.dto.Product
import com.miroshnychenko.catalog.service.CatalogService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/products")
class CatalogController(private val catalogService: CatalogService) {

    @GetMapping("/{uniqId}", produces = [MediaType.APPLICATION_NDJSON_VALUE])
    fun getProductByUniqId(@PathVariable uniqId: String): Mono<Product> {
        return catalogService.findProductByUniqId(uniqId)
    }

    @GetMapping("/sku/{sku}", produces = [MediaType.APPLICATION_NDJSON_VALUE])
    fun getProductsBySku(@PathVariable sku: String): Flux<Product> {
        return catalogService.findProductsBySku(sku)
    }
}