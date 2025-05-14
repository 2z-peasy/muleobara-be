package com.pj2z.pj2zbe.community.controller;

import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.community.dto.PostCreateRequest;
import com.pj2z.pj2zbe.community.dto.PostCreateResponse;
import com.pj2z.pj2zbe.community.service.PostService;
import com.pj2z.pj2zbe.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/posts")
@Tag(name = "커뮤니티 하단_게시글", description = "게시글 생성, 삭제, 수정, 조회 API")
public class PostController {

    private final PostService postService;

    @PostMapping
    @Operation(summary = "게시글 생성", description = "게시글을 생성합니다.")
    public ResponseEntity<PostCreateResponse> createPost(@UserCheck User user,
                                                         @Valid @RequestBody PostCreateRequest request) {
        PostCreateResponse response = postService.create(user, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
