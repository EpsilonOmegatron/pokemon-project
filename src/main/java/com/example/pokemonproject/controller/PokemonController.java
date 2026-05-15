package com.example.pokemonproject.controller;

import com.example.pokemonproject.dto.request.CreatePokemonRequest;
import com.example.pokemonproject.dto.response.PokemonResponse;
import com.example.pokemonproject.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pokemon")
@AllArgsConstructor
public class PokemonController {

    private PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<PokemonResponse>> getPokemon() {
        return ResponseEntity.ok(pokemonService.getAllPokemon());
    }

    @GetMapping("/{name}")
    public ResponseEntity<PokemonResponse> getPokemonByName(@PathVariable String name) {
        return ResponseEntity.ok(pokemonService.getPokemonByName(name));
    }

    @PostMapping
    public ResponseEntity<PokemonResponse> registerNewPokemon(@RequestBody CreatePokemonRequest request) {
        return ResponseEntity.ok(pokemonService.createPokemon(request));
    }
}
