package com.example.pokemonproject.controller;

import com.example.pokemonproject.dto.request.pokemon.AddPokemonAbilityRequest;
import com.example.pokemonproject.dto.request.pokemon.AddPokemonMoveRequest;
import com.example.pokemonproject.dto.request.pokemon.CreatePokemonRequest;
import com.example.pokemonproject.dto.response.PokemonResponse;
import com.example.pokemonproject.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pokemon")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<PokemonResponse>> getPokemon() {
        return ResponseEntity.ok(pokemonService.getAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<PokemonResponse> getPokemonByName(@PathVariable String name) {
        return ResponseEntity.ok(pokemonService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<PokemonResponse> registerNewPokemon(@RequestBody CreatePokemonRequest request) {
        return ResponseEntity.ok(pokemonService.save(request));
    }

    @PatchMapping("/moves")
    public ResponseEntity<PokemonResponse> addMoveToPokemon(@RequestBody AddPokemonMoveRequest request) {
        return ResponseEntity.ok(pokemonService.addMoves(request));
    }

    @PatchMapping("/abilities")
    public ResponseEntity<PokemonResponse> addAbilitiesToPokemon(@RequestBody AddPokemonAbilityRequest request) {
        return ResponseEntity.ok(pokemonService.addAbilities(request));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deletePokemonByName(@PathVariable String name) {
        return ResponseEntity.ok(pokemonService.delete(name));
    }
}
