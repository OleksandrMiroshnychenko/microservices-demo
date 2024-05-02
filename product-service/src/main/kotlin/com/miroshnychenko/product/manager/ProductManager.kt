package com.miroshnychenko.product.manager

import com.miroshnychenko.product.dto.AvailabilityStatus
import com.miroshnychenko.product.dto.Product
import com.miroshnychenko.product.service.CatalogService
import com.miroshnychenko.product.service.InventoryService
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Function

@Component
class ProductManager(private val catalogService: CatalogService, private val inventoryService: InventoryService) {

    fun getProductIfAvailableByUniqId(uniqId: String): Mono<Product> =
        inventoryService.findProductsAvailability(mutableListOf(uniqId))
            .filter { AvailabilityStatus.AVAILABLE == it.availability }
            .flatMap { catalogService.findProductByUniqId(uniqId) }
            .singleOrEmpty()

    fun getProductsIfAvailableBySku(sku: String): Flux<Product?> =
        catalogService.findProductsBySku(sku).collectMap(Product::uniqId, Function.identity())
            .flatMapMany { map ->
                inventoryService.findProductsAvailability(map.keys)
                    .filter { pa -> AvailabilityStatus.AVAILABLE == pa.availability }
                    .map { pa -> map[pa.uniqId] }
            }
}
