package com.pj2z.pj2zbe.community.controller;

import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.community.dto.PostCreateRequest;
import com.pj2z.pj2zbe.community.dto.PostCreateResponse;
import com.pj2z.pj2zbe.community.dto.PostResponse;
import com.pj2z.pj2zbe.community.service.PostService;
import com.pj2z.pj2zbe.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostCreateResponse> createPost(@UserCheck User user,
                                                         @Valid @RequestBody PostCreateRequest request) {
        PostCreateResponse response = postService.create(user, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> retrievePost(@UserCheck User user,
                                                     @PathVariable Long postId) {
        PostResponse response = postService.retrieve(postId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/hot")
    public ResponseEntity<List<PostResponse>> retrieveHotPosts(@UserCheck User user) {
        List<PostResponse> response = postService.retrieveHotPosts();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
