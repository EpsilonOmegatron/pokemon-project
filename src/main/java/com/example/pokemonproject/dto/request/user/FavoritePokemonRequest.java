package com.example.pokemonproject.dto.request.user;

import com.example.pokemonproject.enums.UpdateAction;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FavoritePokemonRequest(

        @NotEmpty(message = "Pokemon are required")
        List<String> pokemon,

        @NotNull(message = "Specify update action")
        UpdateAction action
) {
}
