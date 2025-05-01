package com.pj2z.pj2zbe.community.controller;

import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.community.dto.LikeResponse;
import com.pj2z.pj2zbe.community.service.LikeService;
import com.pj2z.pj2zbe.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/likes")
public class LikeController {

    private final LikeService likeService;

    @PutMapping("/{postId}")
    public ResponseEntity<LikeResponse> likePost(@UserCheck User user,
                                                 @PathVariable Long postId) {
        LikeResponse response = likeService.likePost(user, postId);
        return ResponseEntity.ok(response);
    }
}
