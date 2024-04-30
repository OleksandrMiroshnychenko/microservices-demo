package com.miroshnychenko.catalog.service

import com.miroshnychenko.catalog.dto.Product
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CatalogService(private val data: Flux<Product>) {

    fun findProductByUniqId(uniqId: String): Mono<Product> = data.filter { uniqId == it.uniqId }.singleOrEmpty()

    fun findProductsBySku(sku: String): Flux<Product> = data.filter { sku == it.sku }
}