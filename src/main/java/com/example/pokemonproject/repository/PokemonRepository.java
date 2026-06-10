package com.example.pokemonproject.repository;

import com.example.pokemonproject.entity.Pokemon;
import com.example.pokemonproject.common.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    Optional<Pokemon> findBySlug(String slug);

    List<Pokemon> findByTypesContaining(Type type);
}
