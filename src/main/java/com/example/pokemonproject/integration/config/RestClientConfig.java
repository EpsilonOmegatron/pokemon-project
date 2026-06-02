package com.example.pokemonproject.integration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Value("${pokeapi.base-url}")
    private String baseUrl;

    @Bean
    public RestClient pokeApiRestClient(RestClient.Builder builder) {

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(Duration.ofSeconds(5));
        factory.setReadTimeout(Duration.ofSeconds(5));

        return builder
                .baseUrl(baseUrl)
                .requestFactory(factory)
                .build();
    }
}