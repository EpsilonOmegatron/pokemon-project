package com.example.pokemonproject.dto.request.pokemon;

import com.example.pokemonproject.enums.Type;
import jakarta.validation.constraints.*;

import java.util.Set;

public record CreatePokemonRequest(

        @NotBlank(message = "Pokemon name is required")
        @Size(max = 50, message = "Name must be <= 50 characters")
        String name,

        @NotEmpty(message = "Pokemon must have at least one type")
        Set<Type> types,

        @NotNull(message = "HP is required")
        @Min(1) @Max(255)
        Integer hp,

        @NotNull(message = "Attack is required")
        @Min(1) @Max(255)
        Integer atk,

        @NotNull(message = "Special Attack is required")
        @Min(1) @Max(255)
        Integer spAtk,

        @NotNull(message = "Defense is required")
        @Min(1) @Max(255)
        Integer def,

        @NotNull(message = "Special Defense is required")
        @Min(1) @Max(255)
        Integer spDef,

        @NotNull(message = "Speed is required")
        @Min(1) @Max(255)
        Integer spe,

        @Size(max = 50, message = "Pre-evolution must be <= 50 characters")
        String evolvesFrom,

        @Size(max = 50, message = "Evolution trigger must be <= 50 characters")
        String evolutionTrigger
) {
}