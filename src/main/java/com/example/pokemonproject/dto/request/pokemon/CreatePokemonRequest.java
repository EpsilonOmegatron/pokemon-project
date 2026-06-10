package com.example.pokemonproject.dto.request.pokemon;

import com.example.pokemonproject.common.enums.Type;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

public record CreatePokemonRequest(

        @NotBlank(message = "{validation.required}")
        @Size(min = 1, max = 50, message = "{validation.range}")
        String name,

        @NotEmpty(message = "{validation.required}")
        Set<Type> types,

        @NotNull(message = "{validation.required}")
        @Range(min = 1, max = 255, message = "{validation.range}")
        Integer hp,

        @NotNull(message = "{validation.required}")
        @Range(min = 1, max = 255, message = "{validation.range}")
        Integer atk,

        @NotNull(message = "{validation.required}")
        @Range(min = 1, max = 255, message = "{validation.range}")
        Integer spAtk,

        @NotNull(message = "{validation.required}")
        @Range(min = 1, max = 255, message = "{validation.range}")
        Integer def,

        @NotNull(message = "{validation.required}")
        @Range(min = 1, max = 255, message = "{validation.range}")
        Integer spDef,

        @NotNull(message = "{validation.required}")
        @Range(min = 1, max = 255, message = "{validation.range}")
        Integer spe,

        @Size(min = 1, max = 50, message = "{validation.range}")
        String evolvesFrom,

        @Size(min = 5, max = 50, message = "{validation.range}")
        String evolutionTrigger
) {
}