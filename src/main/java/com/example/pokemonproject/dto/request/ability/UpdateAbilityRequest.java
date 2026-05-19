package com.example.pokemonproject.dto.request.ability;

import jakarta.validation.constraints.Size;

public record UpdateAbilityRequest(

        @Size(max = 50, message = "Ability name must be <= 50 characters")
        String name,

        @Size(max = 100, message = "Ability description must be <= 100 characters")
        String description
) {
}
