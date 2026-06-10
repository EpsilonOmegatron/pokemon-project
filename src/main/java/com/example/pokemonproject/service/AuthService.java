package com.example.pokemonproject.service;

import com.example.pokemonproject.dto.mapper.UserMapper;
import com.example.pokemonproject.dto.request.user.UserRequest;
import com.example.pokemonproject.dto.response.AuthResponse;
import com.example.pokemonproject.dto.response.UserResponse;
import com.example.pokemonproject.entity.User;
import com.example.pokemonproject.exception.ApiException;
import com.example.pokemonproject.repository.RoleRepository;
import com.example.pokemonproject.repository.UserRepository;
import com.example.pokemonproject.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public AuthResponse login(UserRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        return jwtService.generateToken(request.username());
    }

    @Transactional
    public UserResponse register(UserRequest request) {
        User user = new User();

        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new ApiException("Username already exists");
        }

        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.getRoles().add(roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ApiException("Role not found")));

        User saved = userRepository.save(user);

        return UserMapper.mapToUserResponse(saved);
    }
}
