package com.pj2z.pj2zbe.auth.controller;

import com.pj2z.pj2zbe.auth.controller.dto.response.TokenResponse;
import com.pj2z.pj2zbe.auth.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/callback")
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping
    public @ResponseBody TokenResponse kakaoLogin(@RequestParam("code") String code) {
        String kakaoAccessToken = kakaoService.getAccessToken(code);
        TokenResponse token = kakaoService.loginOrSignUp(kakaoAccessToken);
        log.info("로그인 성공 !");
        return token;
    }
}
