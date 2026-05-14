package com.example.pokemonproject.repository;

import com.example.pokemonproject.entity.Move;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoveRepository extends JpaRepository<Move, Integer> {

    Optional<Move> findByNameIgnoreCase(String name);
}
