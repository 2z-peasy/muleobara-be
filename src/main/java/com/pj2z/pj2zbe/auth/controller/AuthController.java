package com.pj2z.pj2zbe.auth.controller;

import com.pj2z.pj2zbe.auth.controller.dto.request.LoginRequest;
import com.pj2z.pj2zbe.auth.controller.dto.request.RefreshTokenRequest;
import com.pj2z.pj2zbe.auth.controller.dto.request.SignupRequest;
import com.pj2z.pj2zbe.auth.controller.dto.response.SignupResponse;
import com.pj2z.pj2zbe.auth.controller.dto.response.TokenResponse;
import com.pj2z.pj2zbe.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest request) {
        SignupResponse response = authService.signup(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        TokenResponse response = authService.login(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshAccessToken(@Valid @RequestBody RefreshTokenRequest request) {
        TokenResponse response = authService.refreshAccessToken(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
