package com.miroshnychenko.product.config

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import io.github.resilience4j.timelimiter.TimeLimiterRegistry
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class CircuitBreakerConfig(
    private val circuitBreakerRegistry: CircuitBreakerRegistry,
    private val timeLimiterRegistry: TimeLimiterRegistry
) {

    @Bean
    fun reactiveResilience4JCircuitBreakerFactory(): ReactiveResilience4JCircuitBreakerFactory {
        val reactiveResilience4JCircuitBreakerFactory =
            ReactiveResilience4JCircuitBreakerFactory(circuitBreakerRegistry, timeLimiterRegistry)
        reactiveResilience4JCircuitBreakerFactory.configureDefault { id: String ->
            this.createResilience4JCircuitBreakerConfiguration(
                id
            )
        }
        return reactiveResilience4JCircuitBreakerFactory
    }

    private fun createResilience4JCircuitBreakerConfiguration(id: String): Resilience4JCircuitBreakerConfiguration {
        val circuitBreaker: CircuitBreaker = circuitBreakerRegistry.circuitBreaker(id)
        val circuitBreakerConfig: CircuitBreakerConfig = circuitBreaker.circuitBreakerConfig
        val timeLimiterConfig = timeLimiterRegistry.timeLimiter(id)
            .timeLimiterConfig
        circuitBreaker.eventPublisher
            .onEvent { event -> println("Circuit-breaker Event Publisher : $event") }
        return Resilience4JConfigBuilder(id)
            .circuitBreakerConfig(circuitBreakerConfig)
            .timeLimiterConfig(timeLimiterConfig)
            .build()
    }
}