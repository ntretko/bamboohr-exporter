package com.ntretko.bamboothingy.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.lang.NonNull

@Configuration
@ConfigurationProperties
class AppConfiguration {
    @NonNull
    String baseURL
    @NonNull
    String loginEndpoint

    @Bean
    Logger logger() {
        Logger logger = LoggerFactory.getLogger("BambooHR")
        return logger
    }
}
