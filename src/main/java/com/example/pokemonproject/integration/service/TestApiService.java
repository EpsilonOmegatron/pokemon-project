package com.example.pokemonproject.integration.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TestApiService {

    private final RestClient restClient = RestClient.create("https://dummyjson.com");

    public String sampleEndpoint() {
        return restClient.get()
                .uri("/test")
                .retrieve()
                .body(String.class);
    }
}
