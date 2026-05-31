package com.example.pokemonproject.integration.controller;

import com.example.pokemonproject.integration.service.TestApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external")
@RequiredArgsConstructor
public class TestApiController {

    private final TestApiService testApiService;

    @GetMapping("/test")
    public String testEndpoint() {
        return testApiService.sampleEndpoint();
    }
}
