package com.example.pokemonproject.dto.mapper;

import com.example.pokemonproject.dto.response.UserResponse;
import com.example.pokemonproject.entity.Pokemon;
import com.example.pokemonproject.entity.User;

public class UserMapper {

    public static UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getUsername(),
                user.getPokemon()
                        .stream()
                        .map(Pokemon::getName)
                        .toList()
        );
    }
}
