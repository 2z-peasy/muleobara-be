package com.pj2z.pj2zbe.auth.service;

import com.pj2z.pj2zbe.auth.controller.dto.response.TokenResponse;
import com.pj2z.pj2zbe.user.entity.Role;
import com.pj2z.pj2zbe.user.entity.User;
import com.pj2z.pj2zbe.user.repository.UserRepository;
import com.pj2z.pj2zbe.common.jwt.JwtUtil;
import com.pj2z.pj2zbe.auth.controller.dto.response.KakaoTokenResDto;
import com.pj2z.pj2zbe.auth.controller.dto.response.KakaoUserInfo;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoService {

    @Value("${kakao.client_id}")
    private String clientId;

    private static final String KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com"; // 액세스 토큰을 발급받기 위한 서버
    private static final String KAUTH_USER_URL_HOST = "https://kapi.kakao.com"; // 사용자 정보를 받아오기 위한 서버

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public String getAccessToken(String code) {

        KakaoTokenResDto kakaoTokenResDto = WebClient.create(KAUTH_TOKEN_URL_HOST).post()// 카카오 인증 서버로 post 요청 준비
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("code", code)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve() // 요청을 보내고 응답 받기
                .bodyToMono(KakaoTokenResDto.class) // 카카오 서버로부터 받아온 응답 본문을 dto로 변환
                .block(); // 비동기 방식으로 처리된 결과를 동기적으로 받을 수 있게

        return kakaoTokenResDto.accessToken();
    }

    public KakaoUserInfo getUserInfo(String accessToken) {

        return WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoUserInfo.class)
                .block();
    }

    @Transactional
    public TokenResponse loginOrSignUp(String kakaoAccessToken) {
        KakaoUserInfo userInfo = getUserInfo(kakaoAccessToken);
        Long id = userInfo.getId();

        User user = userRepository.findById(id).orElseGet(() ->
                userRepository.save(User.builder()
                        .email(userInfo.getKakaoAccount().getEmail())
                        .build())

        );

        return jwtUtil.generateTokens(user.getId());
    }
}
