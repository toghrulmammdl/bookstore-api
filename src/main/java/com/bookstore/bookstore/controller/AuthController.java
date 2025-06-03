package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.dto.request.RegisterRequestDto;
import com.bookstore.bookstore.dto.response.RegisterResponseDto;
import com.bookstore.bookstore.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@Valid @RequestBody RegisterRequestDto request){
        RegisterResponseDto response = authService.registerResponseDto(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/verify")
    public ResponseEntity<RegisterResponseDto> verifyEmail(@RequestParam String token){
        RegisterResponseDto response = authService.verifyEmail(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
