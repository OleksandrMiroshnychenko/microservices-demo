package com.miroshnychenko.product.config

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker
import org.springframework.stereotype.Component
import reactivefeign.cloud2.ReactiveFeignCircuitBreakerFactory

@Component
class ReactiveFeignConfiguration(private val reactiveResilience4JCircuitBreakerFactory: ReactiveResilience4JCircuitBreakerFactory) :
    ReactiveFeignCircuitBreakerFactory {

    override fun apply(reactiveFeignClientName: String): ReactiveCircuitBreaker {
        return reactiveResilience4JCircuitBreakerFactory.create("circuitBreakerId")
    }
}