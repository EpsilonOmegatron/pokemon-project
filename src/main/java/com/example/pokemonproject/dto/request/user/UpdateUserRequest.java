package com.example.pokemonproject.dto.request.user;

import com.example.pokemonproject.common.validation.ValidUsername;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(

        @Size(min = 5, max = 50, message = "{validation.range}")
        @ValidUsername
        String username,

        @Size(min = 10, max = 255, message = "{validation.range}")
        String password
) {
}
