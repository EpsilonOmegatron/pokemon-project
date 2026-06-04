package com.example.pokemonproject.repository;

import com.example.pokemonproject.entity.Ability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbilityRepository extends JpaRepository<Ability, Integer> {

    Optional<Ability> findBySlug(String slug);
}
