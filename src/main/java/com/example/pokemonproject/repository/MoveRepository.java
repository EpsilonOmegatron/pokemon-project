package com.example.pokemonproject.repository;

import com.example.pokemonproject.entity.Move;
import com.example.pokemonproject.enums.DamageCategory;
import com.example.pokemonproject.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MoveRepository extends JpaRepository<Move, Integer> {

    Optional<Move> findByNameIgnoreCase(String name);

    List<Move> findAllByDamageCategory(DamageCategory damageCategory);

    List<Move> findByTypeContaining(Type type);
}
