package com.example.pokemonproject.dto.response;

import java.util.List;

public record UserResponse(String name, List<String> favoritePokemon) {
}
