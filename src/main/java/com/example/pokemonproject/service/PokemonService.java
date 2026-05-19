package com.example.pokemonproject.service;

import com.example.pokemonproject.dto.mapper.PokemonMapper;
import com.example.pokemonproject.dto.request.pokemon.AbilityMoveRequest;
import com.example.pokemonproject.dto.request.pokemon.CreatePokemonRequest;
import com.example.pokemonproject.dto.request.pokemon.UpdatePokemonRequest;
import com.example.pokemonproject.dto.response.PokemonResponse;
import com.example.pokemonproject.entity.Ability;
import com.example.pokemonproject.entity.Move;
import com.example.pokemonproject.entity.Pokemon;
import com.example.pokemonproject.exception.ResourceNotFoundException;
import com.example.pokemonproject.repository.PokemonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final AbilityService abilityService;
    private final MoveService moveService;

    public Pokemon findPokemonByName(String name) {
        return pokemonRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon with name " + name + " doesn't exist."));
    }

    public List<PokemonResponse> getAll() {
        List<Pokemon> pokemon = pokemonRepository.findAll();
        return pokemon.stream().map(PokemonMapper::mapToPokemonResponse).toList();
    }

    public PokemonResponse getByName(String name) {
        Pokemon pokemon = findPokemonByName(name);
        return PokemonMapper.mapToPokemonResponse(pokemon);
    }


    @Transactional
    public PokemonResponse save(CreatePokemonRequest request) {

        Pokemon pre_evo = null;

        if (request.evolvesFrom() != null && !request.evolvesFrom().isBlank()) {
            pre_evo = pokemonRepository.findByNameIgnoreCase(request.evolvesFrom())
                    .orElseThrow(() -> new ResourceNotFoundException("Pre-evo Pokemon with name " + request.evolvesFrom() + " does not exist."));
        }
        Pokemon pokemon = PokemonMapper.mapToPokemon(request, pre_evo);

        Pokemon saved = pokemonRepository.save(pokemon);

        return PokemonMapper.mapToPokemonResponse(saved);
    }

    @Transactional
    public PokemonResponse manageMoves(String name, AbilityMoveRequest request) {
        Pokemon pokemon = findPokemonByName(name);

        for (String moveName : request.abilitiesOrMoves()) {
            Move move = moveService.findMoveByName(moveName);
            switch (request.action()) {
                case ADD -> pokemon.getMoves().add(move);
                case REMOVE -> pokemon.getMoves().remove(move);
            }
        }

        Pokemon saved = pokemonRepository.save(pokemon);

        return PokemonMapper.mapToPokemonResponse(saved);
    }

    @Transactional
    public PokemonResponse manageAbilities(String name, AbilityMoveRequest request) {
        Pokemon pokemon = findPokemonByName(name);

        for (String abilityName : request.abilitiesOrMoves()) {
            Ability ability = abilityService.findAbilityByName(abilityName);
            switch (request.action()) {
                case ADD -> pokemon.getAbilities().add(ability);
                case REMOVE -> pokemon.getAbilities().remove(ability);
            }
        }

        Pokemon saved = pokemonRepository.save(pokemon);

        return PokemonMapper.mapToPokemonResponse(saved);
    }

    @Transactional
    public PokemonResponse update(String pokemonName, UpdatePokemonRequest request) {

        Pokemon pokemon = findPokemonByName(pokemonName);

        if (request.name() != null && !request.name().isBlank()) {
            pokemon.setName(request.name());
        }

        if (request.types() != null && !request.types().isEmpty()) {
            pokemon.setTypes(request.types());
        }

        if (request.hp() != null) {
            pokemon.setHp(request.hp());
        }

        if (request.atk() != null) {
            pokemon.setAtk(request.atk());
        }

        if (request.spAtk() != null) {
            pokemon.setSpAtk(request.spAtk());
        }

        if (request.def() != null) {
            pokemon.setDef(request.def());
        }

        if (request.spDef() != null) {
            pokemon.setSpDef(request.spDef());
        }

        if (request.spe() != null) {
            pokemon.setSpe(request.spe());
        }

        if (request.evolutionTrigger() != null) {
            pokemon.setEvolutionTrigger(request.evolutionTrigger());
        }

        if (request.evolvesFrom() != null) {

            if (request.evolvesFrom().isBlank()) {
                pokemon.setEvolvesFrom(null);
            } else {

                Pokemon preEvolution = findPokemonByName(request.evolvesFrom());

                pokemon.setEvolvesFrom(preEvolution);
            }
        }

        Pokemon saved = pokemonRepository.save(pokemon);

        return PokemonMapper.mapToPokemonResponse(saved);
    }

    @Transactional
    public String delete(String name) {
        Pokemon pokemon = findPokemonByName(name);

        pokemonRepository.delete(pokemon);

        return "Pokemon deleted successfully";
    }
}
