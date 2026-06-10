package com.example.pokemonproject.dto.request.user;

import com.example.pokemonproject.common.validation.ValidUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @NotBlank(message = "{validation.required}")
        @ValidUsername
        String username,

        @NotBlank(message = "{validation.required}")
        @Size(min = 10, max = 255, message = "{validation.range}")
        String password
) {
}
