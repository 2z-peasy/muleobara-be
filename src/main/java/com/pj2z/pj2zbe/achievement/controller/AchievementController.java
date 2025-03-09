package com.pj2z.pj2zbe.achievement.controller;

import com.pj2z.pj2zbe.achievement.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/achievement")
public class AchievementController {

    private final AchievementService achievementService;









    // 2025.03.09 기존 방식 제거
    /*
    @PostMapping
    public RspTemplate<String> createHeart(@Valid @RequestBody LikeSaveRequest request) {
        heartService.saveHeart(request);
        return new RspTemplate<>(HttpStatus.CREATED, "성공적으로 좋아요를 눌렀습니다.");
    }

    @GetMapping
    public RspTemplate<List<LikeResponse>> getHeart() {
        List<LikeResponse> responses = heartService.retrieveAllHearts();x
        return new RspTemplate<>(HttpStatus.OK, "성공적으로 좋아요를 조회했습니다.", responses);
    }

    @GetMapping("/{heartId}")
    public RspTemplate<LikeResponse> getHeart(@PathVariable Long heartId) {
        LikeResponse response = heartService.retrieveOneHeart(heartId);
        return new RspTemplate<>(HttpStatus.OK, "성공적으로 좋아요를 조회했습니다.", response);
    }

    @DeleteMapping("/{heartId}")
    public RspTemplate<String> deleteHeart(@PathVariable Long heartId) {
        heartService.deleteHeart(heartId);
        return new RspTemplate<>(HttpStatus.NO_CONTENT, "성공적으로 좋아요를 취소했습니다.");
    }
     */

}
