package com.miroshnychenko.product.service

import com.miroshnychenko.product.client.InventoryClient
import com.miroshnychenko.product.dto.ProductAvailability
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class InventoryService(private val inventoryClient: InventoryClient) {

    fun findProductsAvailability(uniqIds: MutableCollection<String?>): Flux<ProductAvailability> =
        inventoryClient.getProductsAvailability(uniqIds).onErrorResume { Flux.empty() }
}