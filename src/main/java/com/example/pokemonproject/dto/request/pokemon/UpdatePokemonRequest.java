package com.example.pokemonproject.dto.request.pokemon;

import com.example.pokemonproject.common.enums.Type;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

public record UpdatePokemonRequest(

        @Size(min = 1, max = 50, message = "{validation.range}")
        String name,

        Set<Type> types,

        @Range(min = 1, max = 255, message = "{validation.range}")
        Integer hp,

        @Range(min = 1, max = 255, message = "{validation.range}")
        Integer atk,

        @Range(min = 1, max = 255, message = "{validation.range}")
        Integer spAtk,

        @Range(min = 1, max = 255, message = "{validation.range}")
        Integer def,

        @Range(min = 1, max = 255, message = "{validation.range}")
        Integer spDef,

        @Range(min = 1, max = 255, message = "{validation.range}")
        Integer spe,

        @Size(min = 1, max = 50, message = "{validation.range}")
        String evolvesFrom,

        @Size(min = 1, max = 50, message = "{validation.range}")
        String evolutionTrigger
) {
}
