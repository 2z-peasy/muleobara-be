package com.pj2z.pj2zbe.achievement.controller;

import com.pj2z.pj2zbe.achievement.service.AchievementService;
import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/achievements")
@RequiredArgsConstructor
@Tag(name = "업적 달성", description = "사용자의 업적 달성에 대한 API")
public class AchievementController {

    private final AchievementService achievementService;

    @PostMapping("/likes")
    @Operation(summary = "좋아요 추가", description = "사용자의 좋아요 추가")
    public ResponseEntity<Object> addLike(@UserCheck User user) {
        try {
            Long likeCount = achievementService.addLikeConut(user);

            return ResponseEntity.ok(Map.of(
                    "likeCount", likeCount
            ));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", ex.getMessage()
            ));
        }
    }

    @GetMapping("/likes")
    @Operation(summary = "좋아요 개수 조회", description = "사용자의 좋아요 개수 조회")
    public ResponseEntity<Object> getLikeCount(@UserCheck User user) {
        try {
            return ResponseEntity.ok(Map.of(
                    "likeCount", achievementService.getLikeCount(user)
            ));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", ex.getMessage()
            ));
        }
    }
}
