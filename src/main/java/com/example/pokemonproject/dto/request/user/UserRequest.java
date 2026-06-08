package com.example.pokemonproject.dto.request.user;

import com.example.pokemonproject.common.validation.ValidUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @NotBlank(message = "Username is required")
        @ValidUsername
        String username,

        @NotBlank(message = "Password is required")
        @Size(max = 255, message = "Password must be <= 255 characters")
        String password
) {
}
