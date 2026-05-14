package com.example.pokemonproject.dto.mapper;

import com.example.pokemonproject.dto.response.PokemonResponse;
import com.example.pokemonproject.entity.Ability;
import com.example.pokemonproject.entity.Move;
import com.example.pokemonproject.entity.Pokemon;

import java.util.stream.Collectors;

public class PokemonMapper {
    public static PokemonResponse toPokemonResponse(Pokemon pokemon) {
        return new PokemonResponse(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getTypes(),

                pokemon.getAbilities()
                        .stream()
                        .map(Ability::getName)
                        .collect(Collectors.toSet()),

                pokemon.getMoves()
                        .stream()
                        .map(Move::getName)
                        .collect(Collectors.toSet()),

                pokemon.getHp(),
                pokemon.getAtk(),
                pokemon.getSpAtk(),
                pokemon.getDef(),
                pokemon.getSpDef(),
                pokemon.getSpe(),

                pokemon.getEvolvesFrom() == null ? "None" : pokemon.getEvolvesFrom().getName(),

                pokemon.getEvolutionTrigger()
        );
    }
}
