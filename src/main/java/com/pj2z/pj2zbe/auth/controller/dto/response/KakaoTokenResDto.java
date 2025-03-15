package com.pj2z.pj2zbe.auth.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record KakaoTokenResDto(
        String tokenType,
        String accessToken,
        String idToken,
        Integer expiresIn,
        String refreshToken,
        Integer refreshTokenExpiresIn,
        String scope
) {
}
