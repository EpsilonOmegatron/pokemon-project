package com.example.pokemonproject.integration.service;

import com.example.pokemonproject.integration.dto.PokeApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PokeApiService {

    private final RestClient restClient = RestClient.create("https://pokeapi.co/api/v2");

    public PokeApiResponse getExternalPokemon(String name) {
        return restClient.get()
                .uri("/pokemon/{name}", name.toLowerCase())
                .retrieve()
                .body(PokeApiResponse.class);
    }
}
