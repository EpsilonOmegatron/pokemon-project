package com.example.pokemonproject.dto.request.pokemon;

import com.example.pokemonproject.enums.Type;

import java.util.Set;

public record UpdatePokemonRequest(
        String name,
        Set<Type> types,
        Integer hp,
        Integer atk,
        Integer spAtk,
        Integer def,
        Integer spDef,
        Integer spe,
        String evolvesFrom,
        String evolutionTrigger
) {
}
