package com.example.pokemonproject.integration.controller;

import com.example.pokemonproject.dto.response.PokemonResponse;
import com.example.pokemonproject.integration.dto.PokeApiResponse;
import com.example.pokemonproject.integration.service.PokeApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external")
@RequiredArgsConstructor
public class PokeApiController {

    private final PokeApiService pokeApiService;

    @GetMapping("/pokemon/{name}")
    public ResponseEntity<PokeApiResponse> fetchExternalPokemon(@PathVariable String name) {
        return ResponseEntity.ok(pokeApiService.getExternalPokemon(name));
    }

    @PostMapping("/pokemon/{name}")
    public ResponseEntity<PokemonResponse> saveExternalPokemon(@PathVariable String name) {
        return ResponseEntity.ok(pokeApiService.saveExternalPokemon(name));
    }
}
