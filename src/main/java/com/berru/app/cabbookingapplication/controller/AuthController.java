package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.AuthResponseDTO;
import com.berru.app.cabbookingapplication.dto.LoginRequestDTO;
import com.berru.app.cabbookingapplication.dto.NewUserRequestDTO;
import com.berru.app.cabbookingapplication.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid NewUserRequestDTO newUserRequestDTO) {
        AuthResponseDTO authResponseDTO = authService.register(newUserRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(authResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        AuthResponseDTO authResponseDTO = authService.login(loginRequestDTO);
        return ResponseEntity.ok(authResponseDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        AuthResponseDTO authResponseDTO = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(authResponseDTO);
    }

}
