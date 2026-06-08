package com.example.pokemonproject.dto.request.user;

import com.example.pokemonproject.common.validation.ValidUsername;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(

        @Size(max = 50, message = "Username must be <= 50 characters")
        @ValidUsername
        String username,

        @Size(max = 255, message = "Password must be <= 255 characters")
        String password
) {
}
