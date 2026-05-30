package com.example.pokemonproject.service;

import com.example.pokemonproject.dto.mapper.UserMapper;
import com.example.pokemonproject.dto.request.user.FavoritePokemonRequest;
import com.example.pokemonproject.dto.request.user.UpdateUserRequest;
import com.example.pokemonproject.dto.response.UserResponse;
import com.example.pokemonproject.entity.Pokemon;
import com.example.pokemonproject.entity.User;
import com.example.pokemonproject.exception.DuplicateResourceException;
import com.example.pokemonproject.exception.ResourceNotFoundException;
import com.example.pokemonproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PokemonService pokemonService;
    private final PasswordEncoder passwordEncoder;

    public User findUserByUsername(String name) {
        return userRepository.findByUsername(name).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserResponse).toList();
    }

    public UserResponse getByUsername(String name) {
        User user = findUserByUsername(name);
        return UserMapper.mapToUserResponse(user);
    }

    @Transactional
    @PreAuthorize("#username == authentication.name")
    public UserResponse manageFavorites(String username, FavoritePokemonRequest request) {

        User user = findUserByUsername(username);

        for (String pokemonName : request.pokemon()) {
            Pokemon pokemon = pokemonService.findPokemonByName(pokemonName);

            switch (request.action()) {
                case ADD -> user.getPokemon().add(pokemon);
                case REMOVE -> user.getPokemon().remove(pokemon);
            }
        }

        return UserMapper.mapToUserResponse(user);
    }

    @Transactional
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public UserResponse update(String username, UpdateUserRequest request) {

        User user = findUserByUsername(username);

        if (request.username() != null && !request.username().equalsIgnoreCase(user.getUsername())) {

            if (userRepository.findByUsername(request.username()).isPresent()) {
                throw new DuplicateResourceException("User with username " + request.username() + " already exists.");
            }

            user.setUsername(request.username());
        }

        if (request.password() != null) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }

        return UserMapper.mapToUserResponse(user);
    }

    @Transactional
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public String delete(String username) {
        userRepository.delete(findUserByUsername(username));
        return "User deleted successfully!";
    }
}
