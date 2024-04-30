package com.miroshnychenko.product.service

import com.miroshnychenko.product.client.CatalogClient
import com.miroshnychenko.product.client.InventoryClient
import com.miroshnychenko.product.dto.AvailabilityStatus
import com.miroshnychenko.product.dto.Product
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Function

@Service
class ProductService(private val catalogClient: CatalogClient, private val inventoryClient: InventoryClient) {

    fun getProductIfAvailableByUniqId(uniqId: String): Mono<Product> = inventoryClient.getProduct(mutableListOf(uniqId))
        .filter { AvailabilityStatus.AVAILABLE == it.availability }
        .flatMap { catalogClient.getProductByUniqId(uniqId) }
        .singleOrEmpty()

    fun getProductsIfAvailableBySku(sku: String): Flux<Product?> =
        catalogClient.getProductsBySku(sku).collectMap(Product::uniqId, Function.identity())
            .flatMapMany { map ->
                inventoryClient.getProduct(map.keys)
                    .filter { pa -> AvailabilityStatus.AVAILABLE == pa.availability }
                    .map { pa -> map[pa.uniqId] }
            }
}
