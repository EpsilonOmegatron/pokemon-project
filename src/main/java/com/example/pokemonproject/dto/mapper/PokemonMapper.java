package com.example.pokemonproject.dto.mapper;

import com.example.pokemonproject.dto.request.CreatePokemonRequest;
import com.example.pokemonproject.dto.response.PokemonResponse;
import com.example.pokemonproject.entity.Ability;
import com.example.pokemonproject.entity.Move;
import com.example.pokemonproject.entity.Pokemon;

import java.util.stream.Collectors;

public class PokemonMapper {

    public static PokemonResponse mapToPokemonResponse(Pokemon pokemon) {
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

                pokemon.getEvolvesFrom() == null ? null : pokemon.getEvolvesFrom().getName(),

                pokemon.getEvolutionTrigger()
        );
    }

    public static Pokemon mapToPokemon(CreatePokemonRequest createPokemonRequest, Pokemon evolvesFrom) {

        Pokemon pokemon = new Pokemon();

        // Name
        pokemon.setName(createPokemonRequest.name());

        // Types
        pokemon.setTypes(createPokemonRequest.types());

        // Stats
        pokemon.setHp(createPokemonRequest.hp());
        pokemon.setAtk(createPokemonRequest.atk());
        pokemon.setSpAtk(createPokemonRequest.spAtk());
        pokemon.setDef(createPokemonRequest.def());
        pokemon.setSpDef(createPokemonRequest.spDef());
        pokemon.setSpe(createPokemonRequest.spe());

        // Evolves from
        if (evolvesFrom != null) {
            pokemon.setEvolvesFrom(evolvesFrom);
        }

        // Evolution Trigger
        pokemon.setEvolutionTrigger(createPokemonRequest.evolutionTrigger());

//        // Moves
//        Set<Move> moves = createPokemonRequest.moves()
//                .stream()
//                .map(name -> moveRepository.findByNameIgnoreCase(name)
//                        .orElseThrow(() -> new ResourceNotFoundException("Move with name " + name + " doesn't exist.")))
//                .collect(Collectors.toSet());
//        pokemon.setMoves(moves);
//
//        // Abilities
//        Set<Ability> abilities = createPokemonRequest.abilities()
//                .stream()
//                .map(name -> abilityRepository.findByNameIgnoreCase(name)
//                        .orElseThrow(() -> new ResourceNotFoundException("Ability with name " + name + " doesn't exist.")))
//                .collect(Collectors.toSet());
//        pokemon.setAbilities(abilities);

        return pokemon;
    }
}
