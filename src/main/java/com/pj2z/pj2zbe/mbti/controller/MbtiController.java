package com.pj2z.pj2zbe.mbti.controller;

import com.pj2z.pj2zbe.common.jwt.JwtUtil;
import com.pj2z.pj2zbe.mbti.dto.MbtiMakeRequest;
import com.pj2z.pj2zbe.mbti.dto.MbtiSelectDetailResponse;
import com.pj2z.pj2zbe.mbti.dto.MbtilSelectAllResponse;
import com.pj2z.pj2zbe.mbti.entity.Mbti;
import com.pj2z.pj2zbe.mbti.service.MbtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users/mbti")
public class MbtiController {

    @Autowired
    MbtiService mbtiService;

    private final JwtUtil jwtUtil;

    @Autowired
    public MbtiController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
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


    @GetMapping
    public ResponseEntity<Object> selectUsersMbti(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer ", "");

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "로그인 정보가 유효하지 않습니다."));
        }
        Long userId = Long.valueOf(jwtUtil.getUsernameFromToken(token));
        Mbti mbti = mbtiService.selectUsersMbti(userId);
        return ResponseEntity.ok(Map.of(
                "mbti", mbti.getMbtiType()
        ));
    }
    @GetMapping("/detail")
    public ResponseEntity<Object> selectUsersMbtidetail(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer ", "");

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "로그인 정보가 유효하지 않습니다."));
        }
        Long userId = Long.valueOf(jwtUtil.getUsernameFromToken(token));
        Mbti mbti = mbtiService.selectUsersMbti(userId);
        return ResponseEntity.ok(new MbtiSelectDetailResponse(mbti));
    }
    @GetMapping("/all")
    public ResponseEntity<Object> getUserMbtiList(@RequestHeader("Authorization") String token,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        token = token.replace("Bearer ", "");

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "로그인 정보가 유효하지 않습니다."));
        }
        Long userId = Long.valueOf(jwtUtil.getUsernameFromToken(token));
        try {
            Page<MbtilSelectAllResponse> mbtiPage = mbtiService.getUserMbtiList(userId, page, size);
            return ResponseEntity.ok(mbtiPage);
        }catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", ex.getMessage()
            ));
        }

    }


}
