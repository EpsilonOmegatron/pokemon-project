package com.example.pokemonproject.integration.controller;

import com.example.pokemonproject.integration.dto.PokeApiResponse;
import com.example.pokemonproject.integration.service.PokeApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external")
@RequiredArgsConstructor
public class PokeApiController {

    private final PokeApiService testApiService;

    @GetMapping("/pokemon/{name}")
    public ResponseEntity<PokeApiResponse> testEndpoint(@PathVariable String name) {
        return ResponseEntity.ok(testApiService.getExternalPokemon(name));
    }
}
