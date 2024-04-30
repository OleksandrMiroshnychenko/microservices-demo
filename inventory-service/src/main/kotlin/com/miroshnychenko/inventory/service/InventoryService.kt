package com.miroshnychenko.inventory.service

import com.miroshnychenko.inventory.dto.ProductAvailability
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class InventoryService(private val data: Flux<ProductAvailability>) {

    fun findProductsAvailability(uniqIds: List<String>): Flux<ProductAvailability> =
        data.filter { uniqIds.contains(it.uniqId) }
}