package com.example.pokemonproject.repository;

import com.example.pokemonproject.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    Optional<Pokemon> findByNameIgnoreCase(String name);
}
