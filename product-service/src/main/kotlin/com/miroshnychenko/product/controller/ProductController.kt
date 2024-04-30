package com.miroshnychenko.product.controller

import com.miroshnychenko.product.client.CatalogClient
import com.miroshnychenko.product.client.InventoryClient
import com.miroshnychenko.product.dto.Product
import com.miroshnychenko.product.dto.ProductAvailability
import com.miroshnychenko.product.service.ProductService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    @GetMapping("/{uniqId}", produces = [MediaType.APPLICATION_NDJSON_VALUE])
    fun getProductByUniqId(@PathVariable uniqId: String): Mono<Product> {
        return productService.getProductIfAvailableByUniqId(uniqId)
    }

    @PostMapping("/sku/{sku}", produces = [MediaType.APPLICATION_NDJSON_VALUE])
    fun getProductsBySku(@PathVariable sku: String): Flux<Product?> {
        return productService.getProductsIfAvailableBySku(sku)
    }
}