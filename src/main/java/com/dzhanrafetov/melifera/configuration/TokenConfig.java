package com.dzhanrafetov.melifera.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfig {

    @Value("${token.expiration.minutes}")
    private int tokenExpirationMinutes;

    @Bean
    public int tokenExpirationMinutes() {
        return tokenExpirationMinutes;
    }

    @Bean
    public long tokenExpirationMillis() {
        return tokenExpirationMinutes * 60 * 1000L; // Convert minutes to milliseconds
    }
}
