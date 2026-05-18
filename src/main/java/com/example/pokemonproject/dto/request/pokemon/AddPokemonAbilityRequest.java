package com.example.pokemonproject.dto.request.pokemon;

import java.util.List;

public record AddPokemonAbilityRequest(String pokemon, List<String> abilities) {
}
