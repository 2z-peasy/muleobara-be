package com.pj2z.pj2zbe.auth.controller;

import com.pj2z.pj2zbe.auth.controller.dto.request.LoginRequest;
import com.pj2z.pj2zbe.auth.controller.dto.request.RefreshTokenRequest;
import com.pj2z.pj2zbe.auth.controller.dto.request.SignupRequest;
import com.pj2z.pj2zbe.auth.controller.dto.response.SignupResponse;
import com.pj2z.pj2zbe.auth.controller.dto.response.TokenResponse;
import com.pj2z.pj2zbe.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name="인증", description="회원가입, 로그인, 토큰 재발급에 대한 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "일반 회원가입", description = "일반 회원가입")
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest request) {
        SignupResponse response = authService.signup(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "일반 로그인", description = "일반 로그인")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        TokenResponse response = authService.login(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh 토큰 재발급", description = "Refresh 토큰 재발급")
    public ResponseEntity<TokenResponse> refreshAccessToken(@Valid @RequestBody RefreshTokenRequest request) {
        TokenResponse response = authService.refreshAccessToken(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
