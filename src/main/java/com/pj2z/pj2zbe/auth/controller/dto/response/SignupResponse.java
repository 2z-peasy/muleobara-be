package com.pj2z.pj2zbe.auth.controller.dto.response;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Builder
public record SignupResponse(
        String email,
        List<GrantedAuthority> authorities
) {
    public static SignupResponse of(String email) {
        return SignupResponse.builder()
                .email(email)
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
                .build();
    }
}

