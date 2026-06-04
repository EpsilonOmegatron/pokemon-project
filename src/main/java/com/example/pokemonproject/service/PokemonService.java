package com.example.pokemonproject.service;

import com.example.pokemonproject.dto.mapper.PokemonMapper;
import com.example.pokemonproject.dto.request.pokemon.AbilityMoveRequest;
import com.example.pokemonproject.dto.request.pokemon.CreatePokemonRequest;
import com.example.pokemonproject.dto.request.pokemon.UpdatePokemonRequest;
import com.example.pokemonproject.dto.response.PokemonResponse;
import com.example.pokemonproject.entity.Ability;
import com.example.pokemonproject.entity.Move;
import com.example.pokemonproject.entity.Pokemon;
import com.example.pokemonproject.enums.Type;
import com.example.pokemonproject.exception.ResourceNotFoundException;
import com.example.pokemonproject.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final AbilityService abilityService;
    private final MoveService moveService;
    private final PokemonMapper pokemonMapper;

    public Pokemon findPokemonByName(String name) {
        return pokemonRepository.findBySlug(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon with name " + name + " doesn't exist."));
    }

    public List<PokemonResponse> getAll() {
        List<Pokemon> pokemon = pokemonRepository.findAll();
        return pokemon.stream().map(pokemonMapper::mapToPokemonResponse).toList();
    }

    public PokemonResponse getByName(String name) {
        Pokemon pokemon = findPokemonByName(name);
        return pokemonMapper.mapToPokemonResponse(pokemon);
    }

    public List<PokemonResponse> getAllByType(String type) {
        Type parsedType;
        try {
            parsedType = Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid type " + type);
        }

        List<Pokemon> pokemon = pokemonRepository.findByTypesContaining(parsedType);
        return pokemon.stream().map(pokemonMapper::mapToPokemonResponse).toList();
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public PokemonResponse save(CreatePokemonRequest request) {

        Pokemon pre_evo = null;

        if (request.evolvesFrom() != null && !request.evolvesFrom().isBlank()) {
            pre_evo = pokemonRepository.findBySlug(request.evolvesFrom())
                    .orElseThrow(() -> new ResourceNotFoundException("Pre-evo Pokemon with name " + request.evolvesFrom() + " does not exist."));
        }

        Pokemon pokemon = pokemonMapper.mapToPokemon(request);
        pokemon.setEvolvesFrom(pre_evo);

        Pokemon saved = pokemonRepository.save(pokemon);
        return pokemonMapper.mapToPokemonResponse(saved);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public PokemonResponse manageMoves(String name, AbilityMoveRequest request) {
        Pokemon pokemon = findPokemonByName(name);

        for (String moveName : request.abilitiesOrMoves()) {
            Move move = moveService.findMoveByName(moveName);
            switch (request.action()) {
                case ADD -> pokemon.getMoves().add(move);
                case REMOVE -> pokemon.getMoves().remove(move);
            }
        }

        return pokemonMapper.mapToPokemonResponse(pokemon);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public PokemonResponse manageAbilities(String name, AbilityMoveRequest request) {
        Pokemon pokemon = findPokemonByName(name);

        for (String abilityName : request.abilitiesOrMoves()) {
            Ability ability = abilityService.findAbilityByName(abilityName);
            switch (request.action()) {
                case ADD -> pokemon.getAbilities().add(ability);
                case REMOVE -> pokemon.getAbilities().remove(ability);
            }
        }

        return pokemonMapper.mapToPokemonResponse(pokemon);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public PokemonResponse update(String pokemonName, UpdatePokemonRequest request) {
        Pokemon pokemon = findPokemonByName(pokemonName);

        if (request.evolvesFrom() != null) {
            if (request.evolvesFrom().isBlank()) {
                pokemon.setEvolvesFrom(null);
            } else {
                Pokemon pre_evo = findPokemonByName(request.evolvesFrom());
                pokemon.setEvolvesFrom(pre_evo);
            }
        }

        pokemonMapper.updateFromRequest(request, pokemon);

        return pokemonMapper.mapToPokemonResponse(pokemon);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(String name) {
        Pokemon pokemon = findPokemonByName(name);
        pokemonRepository.delete(pokemon);
        return "Pokemon deleted successfully";
    }
}
