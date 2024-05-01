package com.miroshnychenko.inventory.controller

import com.miroshnychenko.inventory.dto.ProductAvailability
import com.miroshnychenko.inventory.service.InventoryService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/inventory")
class InventoryController(private val inventoryService: InventoryService) {

    @PostMapping("/availability", produces = [MediaType.APPLICATION_NDJSON_VALUE])
    fun getProductsBySku(@RequestBody uniqIds: List<String>): Flux<ProductAvailability> {
        return inventoryService.findProductsAvailability(uniqIds)
    }
}