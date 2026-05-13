package com.example.pokemonproject.repository;

import com.example.pokemonproject.entity.Move;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoveRepository extends JpaRepository<Move, Integer> {
}
