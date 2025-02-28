package com.pj2z.pj2zbe.mbti.controller;

import com.pj2z.pj2zbe.common.jwt.JwtUtil;
import com.pj2z.pj2zbe.mbti.dto.MbtiMakeRequest;
import com.pj2z.pj2zbe.mbti.service.MbtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users/mbti")
public class MbtiController {

    @Autowired
    MbtiService mbtiService;

    private final JwtUtil jwtUtil;

    @Autowired
    public MbtiController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/")
    public ResponseEntity<Object> saveMbti(@RequestHeader("Authorization") String token, @RequestBody MbtiMakeRequest request){
         token = token.replace("Bearer ", "");

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "로그인 정보가 유효하지 않습니다."));
        }
        Long userId = Long.valueOf(jwtUtil.getUsernameFromToken(token));
        mbtiService.saveMbti(userId, request);
        return ResponseEntity.ok(Map.of(
                "mbti", request.getMbti(),
                "message", "MBTI 정보가 저장되었습니다."
        ));
    }



}
