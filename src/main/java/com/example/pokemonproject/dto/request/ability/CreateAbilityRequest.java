package com.example.pokemonproject.dto.request.ability;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAbilityRequest(

        @NotBlank(message = "Ability name is required")
        @Size(max = 50, message = "Ability name must be <= 50 characters")
        String name,

        @NotBlank(message = "Ability description is required")
        @Size(max = 100, message = "Ability description must be <= 100 characters")
        String description
) {
}
