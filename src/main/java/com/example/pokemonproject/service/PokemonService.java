package com.example.pokemonproject.service;

import com.example.pokemonproject.dto.mapper.PokemonMapper;
import com.example.pokemonproject.dto.request.CreatePokemonRequest;
import com.example.pokemonproject.dto.response.PokemonResponse;
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

    private PokemonRepository pokemonRepository;

    public List<PokemonResponse> getAllPokemon() {
        List<Pokemon> pokemon = pokemonRepository.findAll();
        return pokemon.stream().map(PokemonMapper::mapToPokemonResponse).toList();
    }

    public PokemonResponse getPokemonByName(String name) {
        Pokemon pokemon = pokemonRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon with name " + name + " doesn't exist."));
        return PokemonMapper.mapToPokemonResponse(pokemon);
    }

    @Transactional
    public PokemonResponse createPokemon(CreatePokemonRequest request) {

        Pokemon pre_evo = null;

        if (request.evolvesFrom() != null && !request.evolvesFrom().isBlank()) {
            pre_evo = pokemonRepository.findByNameIgnoreCase(request.evolvesFrom())
                    .orElseThrow(() -> new ResourceNotFoundException("Pre-evo pokemon with name " + request.evolvesFrom() + " does not exist."));
        }
        Pokemon pokemon = PokemonMapper.mapToPokemon(request, pre_evo);

        Pokemon saved = pokemonRepository.save(pokemon);

        return PokemonMapper.mapToPokemonResponse(saved);
    }
}
