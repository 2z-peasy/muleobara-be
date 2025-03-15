package com.pj2z.pj2zbe.auth.service;

import com.pj2z.pj2zbe.auth.controller.dto.request.LoginRequest;
import com.pj2z.pj2zbe.auth.controller.dto.request.RefreshTokenRequest;
import com.pj2z.pj2zbe.auth.controller.dto.request.SignupRequest;
import com.pj2z.pj2zbe.auth.controller.dto.response.SignupResponse;
import com.pj2z.pj2zbe.auth.controller.dto.response.TokenResponse;
import com.pj2z.pj2zbe.user.entity.User;
import com.pj2z.pj2zbe.auth.exception.InvalidEmailException;
import com.pj2z.pj2zbe.auth.exception.InvalidNicknameException;
import com.pj2z.pj2zbe.user.repository.UserRepository;
import com.pj2z.pj2zbe.common.exception.CustomAuthenticationException;
import com.pj2z.pj2zbe.common.jwt.JwtUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RedisTemplate redisTemplate;

    public SignupResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("해당 이메일이 존재합니다.");
        }

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
        userRepository.save(user);

        return SignupResponse.of(user.getEmail());
    }

    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new InvalidEmailException("해당 이메일이 존재하지 않습니다."));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidNicknameException("비밀번호가 일치하지 않습니다.");
        }

        TokenResponse response = jwtUtil.generateTokens(user.getId());
        return response;
    }

    public TokenResponse refreshAccessToken(@Valid RefreshTokenRequest request) {
        String username = jwtUtil.getUsernameFromToken(request.refreshToken());

        // Redis에서 RefreshToken 조회 및 검증
        String storedToken = redisTemplate.opsForValue().get("refreshToken:" + username).toString();
        if (storedToken == null || !storedToken.equals(request.refreshToken())) {
            throw new CustomAuthenticationException("Refresh Token이 유효하지 않습니다.");
        }

        // 새로운 Access Token 생성
        String newAccessToken = jwtUtil.generateAccessToken(username, new Date());
        return new TokenResponse(newAccessToken, request.refreshToken());
    }
}
