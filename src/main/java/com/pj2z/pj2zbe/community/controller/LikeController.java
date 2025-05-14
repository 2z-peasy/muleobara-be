package com.pj2z.pj2zbe.community.controller;

import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.community.dto.LikeResponse;
import com.pj2z.pj2zbe.community.service.LikeService;
import com.pj2z.pj2zbe.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/likes")
@Tag(name="커뮤니티 하단_좋아요", description="특정 글에 대한 좋아요 및 좋아요 취소 API")
public class LikeController {

    private final LikeService likeService;

    @PutMapping("/{postId}")
    @Operation(summary = "좋아요", description = "특정 글에 좋아요를 추가하거나 취소합니다.")
    public ResponseEntity<LikeResponse> likePost(@UserCheck User user,
                                                 @PathVariable Long postId) {
        LikeResponse response = likeService.likePost(user, postId);
        return ResponseEntity.ok(response);
    }
}
