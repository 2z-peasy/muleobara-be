package com.pj2z.pj2zbe.auth.controller;

import com.pj2z.pj2zbe.auth.controller.dto.response.TokenResponse;
import com.pj2z.pj2zbe.auth.service.KakaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/callback")
@Tag(name = "카카오 로그인 및 회원가입", description = "카카오 로그인 및 회원가입에 대한 API")
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping
    @Operation(summary = "카카오 로그인 및 회원가입", description = "카카오 로그인 및 회원가입")
    public @ResponseBody TokenResponse kakaoLogin(@RequestParam("code") String code) {
        String kakaoAccessToken = kakaoService.getAccessToken(code);
        TokenResponse token = kakaoService.loginOrSignUp(kakaoAccessToken);
        log.info("로그인 성공 !");
        return token;
    }
}
