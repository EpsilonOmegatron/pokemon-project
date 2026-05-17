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

    public static Pokemon mapToPokemon(CreatePokemonRequest request, Pokemon evolvesFrom) {

        Pokemon pokemon = new Pokemon();

        // Name
        pokemon.setName(request.name());

        // Types
        pokemon.setTypes(request.types());

        // Stats
        pokemon.setHp(request.hp());
        pokemon.setAtk(request.atk());
        pokemon.setSpAtk(request.spAtk());
        pokemon.setDef(request.def());
        pokemon.setSpDef(request.spDef());
        pokemon.setSpe(request.spe());

        // Evolves from
        if (evolvesFrom != null) {
            pokemon.setEvolvesFrom(evolvesFrom);
        }

        // Evolution Trigger
        pokemon.setEvolutionTrigger(request.evolutionTrigger());

//        // Moves
//        Set<Move> moves = request.moves()
//                .stream()
//                .map(name -> moveRepository.findByNameIgnoreCase(name)
//                        .orElseThrow(() -> new ResourceNotFoundException("Move with name " + name + " doesn't exist.")))
//                .collect(Collectors.toSet());
//        pokemon.setMoves(moves);
//
//        // Abilities
//        Set<Ability> abilities = request.abilities()
//                .stream()
//                .map(name -> abilityRepository.findByNameIgnoreCase(name)
//                        .orElseThrow(() -> new ResourceNotFoundException("Ability with name " + name + " doesn't exist.")))
//                .collect(Collectors.toSet());
//        pokemon.setAbilities(abilities);

        return pokemon;
    }
}
