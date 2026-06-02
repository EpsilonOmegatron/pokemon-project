package com.example.pokemonproject.integration.dto;

import com.example.pokemonproject.entity.Pokemon;
import com.example.pokemonproject.enums.Type;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PokeApiMapper {

    public Pokemon mapPokeApiResponse(PokeApiResponse fetchedPokemon) {

        Pokemon pokemon = new Pokemon();

        pokemon.setName(fetchedPokemon.name());

        pokemon.setTypes(fetchedPokemon.types()
                .stream()
                .map(typeSlot -> typeSlot.type().name())
                .map(String::toUpperCase)
                .map(Type::valueOf)
                .collect(Collectors.toSet()));

        Map<String, Integer> fetchedStats = fetchedPokemon.stats()
                .stream()
                .collect(Collectors.toMap(statSlot -> statSlot.stat().name(), PokeApiResponse.StatSlot::baseStat));

        pokemon.setHp(fetchedStats.getOrDefault("hp", 0));
        pokemon.setAtk(fetchedStats.getOrDefault("attack", 0));
        pokemon.setDef(fetchedStats.getOrDefault("defense", 0));
        pokemon.setSpAtk(fetchedStats.getOrDefault("special-attack", 0));
        pokemon.setSpDef(fetchedStats.getOrDefault("special-defense", 0));
        pokemon.setSpe(fetchedStats.getOrDefault("speed", 0));

        return pokemon;
    }
}
