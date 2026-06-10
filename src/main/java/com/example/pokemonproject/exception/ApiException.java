package com.example.pokemonproject.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
