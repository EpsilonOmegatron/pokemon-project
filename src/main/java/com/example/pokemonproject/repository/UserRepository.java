package com.example.pokemonproject.repository;

import com.example.pokemonproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
