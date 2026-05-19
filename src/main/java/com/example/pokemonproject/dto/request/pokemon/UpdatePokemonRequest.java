package com.example.pokemonproject.dto.request.pokemon;

import com.example.pokemonproject.enums.Type;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UpdatePokemonRequest(

        @Size(max = 50, message = "Name must be <= 50 characters")
        String name,

        Set<Type> types,

        @Min(1) @Max(255)
        Integer hp,

        @Min(1) @Max(255)
        Integer atk,

        @Min(1) @Max(255)
        Integer spAtk,

        @Min(1) @Max(255)
        Integer def,

        @Min(1) @Max(255)
        Integer spDef,

        @Min(1) @Max(255)
        Integer spe,

        @Size(max = 50, message = "Pre-evolution must be <= 50 characters")
        String evolvesFrom,

        @Size(max = 50, message = "Evolution trigger must be <= 50 characters")
        String evolutionTrigger
) {
}
