package com.pj2z.pj2zbe.achievement.controller;

import com.pj2z.pj2zbe.achievement.service.AchievementService;
import com.pj2z.pj2zbe.common.jwt.JwtUtil;
import com.pj2z.pj2zbe.common.template.RspTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/achievement")
public class AchievementController {

    @Autowired
    AchievementService achievementService;


    private final JwtUtil jwtUtil;
    @Autowired
    public AchievementController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/like")
    public ResponseEntity<Object> addLike(@RequestHeader("Authorization") String token)
    {
        token = token.replace("Bearer ", "");

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "로그인 정보가 유효하지 않습니다."));
        }
        Long userId = Long.valueOf(jwtUtil.getUsernameFromToken(token));
        try {
            Long likeCount = achievementService.addLikeConut(userId);

            return ResponseEntity.ok(Map.of(
                    "likeCount", likeCount
            ));
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", ex.getMessage()
            ));
        }
    }

    @GetMapping("/like")
    public ResponseEntity<Object>  getLikeCount(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "로그인 정보가 유효하지 않습니다."));
        }
        Long userId = Long.valueOf(jwtUtil.getUsernameFromToken(token));
        try {
        return ResponseEntity.ok(Map.of(
                "likeCount",achievementService.getLikeCount(userId)
                ));
       }
          catch (Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "message", ex.getMessage()
        ));
    }
    }


}
