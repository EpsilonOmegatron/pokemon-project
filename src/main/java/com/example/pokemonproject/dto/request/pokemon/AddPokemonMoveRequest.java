package com.example.pokemonproject.dto.request.pokemon;

import java.util.List;

public record AddPokemonMoveRequest(String pokemon, List<String> moves) {
}
