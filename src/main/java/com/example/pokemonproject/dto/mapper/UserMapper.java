package com.example.pokemonproject.dto.mapper;

import com.example.pokemonproject.dto.request.user.CreateUserRequest;
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

    public static User mapToUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(request.password());
        return user;
    }
}
