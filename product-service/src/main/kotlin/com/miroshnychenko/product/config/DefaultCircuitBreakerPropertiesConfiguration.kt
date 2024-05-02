package com.miroshnychenko.product.config

import io.github.resilience4j.circuitbreaker.autoconfigure.CircuitBreakerProperties
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigurationProperties
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Configuration
import java.util.*


@Configuration
class DefaultCircuitBreakerPropertiesConfiguration : BeanPostProcessor {
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        if (bean is CircuitBreakerProperties) {
            bean.instances
                .values
                .stream()
                .filter { instanceProperties: CircuitBreakerConfigurationProperties.InstanceProperties ->
                    Objects.isNull(
                        instanceProperties.baseConfig
                    )
                }
                .forEach { instanceProperties: CircuitBreakerConfigurationProperties.InstanceProperties ->
                    instanceProperties.setBaseConfig(
                        "default"
                    )
                }
        }
        return bean
    }
}