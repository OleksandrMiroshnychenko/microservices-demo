package com.miroshnychenko.product.service

import com.miroshnychenko.product.client.CatalogClient
import com.miroshnychenko.product.dto.Product
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CatalogService(private val catalogClient: CatalogClient) {

    fun findProductByUniqId(uniqId: String): Mono<Product> =
        catalogClient.getProductByUniqId(uniqId).onErrorResume { Mono.empty() }

    fun findProductsBySku(sku: String): Flux<Product> =
        catalogClient.getProductsBySku(sku).onErrorResume { Flux.empty() }
}