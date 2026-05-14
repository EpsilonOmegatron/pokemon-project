package com.example.pokemonproject.service;

import com.example.pokemonproject.dto.mapper.PokemonMapper;
import com.example.pokemonproject.dto.response.PokemonResponse;
import com.example.pokemonproject.entity.Pokemon;
import com.example.pokemonproject.exception.ResourceNotFoundException;
import com.example.pokemonproject.repository.PokemonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        Pokemon pokemon = pokemonRepository.findPokemonByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon with name " + name + " doesn't exist."));
        return PokemonMapper.mapToPokemonResponse(pokemon);
    }
}
