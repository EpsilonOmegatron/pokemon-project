package com.example.pokemonproject.integration.dto;

import com.example.pokemonproject.entity.Pokemon;
import com.example.pokemonproject.common.enums.Type;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PokeApiMapper {

    public Pokemon mapPokeApiResponse(ApiPokemonResponse fetchedPokemon) {

        Pokemon pokemon = new Pokemon();

        pokemon.setName(fetchedPokemon.name());

        pokemon.setTypes(fetchedPokemon.types()
                .stream()
                .map(typeSlot -> typeSlot.type().name())
                .map(String::toUpperCase)
                .map(Type::valueOf)
                .collect(Collectors.toSet()));

        fetchedPokemon.stats().forEach(stat -> {
            switch (stat.stat().name()) {
                case "hp" -> pokemon.setHp(stat.baseStat());
                case "attack" -> pokemon.setAtk(stat.baseStat());
                case "defense" -> pokemon.setDef(stat.baseStat());
                case "special-attack" -> pokemon.setSpAtk(stat.baseStat());
                case "special-defense" -> pokemon.setSpDef(stat.baseStat());
                case "speed" -> pokemon.setSpe(stat.baseStat());
            }
        });

        return pokemon;
    }
}
