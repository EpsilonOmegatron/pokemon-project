package com.example.pokemonproject.dto.response;

import com.example.pokemonproject.enums.Type;

import java.util.Map;
import java.util.Set;

public record PokemonResponse(
        Integer id,
        String name,
        Set<Type> types,
        Set<String> abilities,
        Set<String> moves,
        Integer hp,
        Integer atk,
        Integer spAtk,
        Integer def,
        Integer spDef,
        Integer spe,
        String evolvesFrom,
        String evolutionTrigger,
        Map<Type, Double> typeDefenses
) {
}
