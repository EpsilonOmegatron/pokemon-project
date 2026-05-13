package com.example.pokemonproject.repository;

import com.example.pokemonproject.entity.Ability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbilityRepository extends JpaRepository<Ability, Integer> {
}
