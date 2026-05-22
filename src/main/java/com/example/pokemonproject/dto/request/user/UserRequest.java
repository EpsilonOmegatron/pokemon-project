package com.example.pokemonproject.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @NotBlank(message = "Username is required")
        @Size(max = 50, message = "Username must be <= 50 characters")
        String username,

        @NotBlank(message = "Password is required")
        @Size(max = 255, message = "Password must be <= 255 characters")
        String password
) {
}
