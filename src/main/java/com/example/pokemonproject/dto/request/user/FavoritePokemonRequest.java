package com.example.pokemonproject.dto.request.user;

import com.example.pokemonproject.enums.UpdateAction;

import java.util.List;

public record FavoritePokemonRequest(List<String> pokemon, UpdateAction action) {
}
