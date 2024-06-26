package com.miroshnychenko.product.client

import com.miroshnychenko.product.dto.ProductAvailability
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Flux

@ReactiveFeignClient(
    name = "inventoryFeignClient",
    url = "http://localhost:8083"
)
interface InventoryClient {

    @PostMapping("/inventory/availability")
    fun getProductsAvailability(@RequestBody uniqIds: MutableCollection<String?>): Flux<ProductAvailability>
}