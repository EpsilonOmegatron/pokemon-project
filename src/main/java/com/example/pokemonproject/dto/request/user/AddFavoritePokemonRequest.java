package com.example.pokemonproject.dto.request.user;

import java.util.List;

public record AddFavoritePokemonRequest(String username, List<String> pokemon) {
}
