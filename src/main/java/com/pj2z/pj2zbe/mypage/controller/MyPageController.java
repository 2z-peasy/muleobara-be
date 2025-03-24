package com.pj2z.pj2zbe.mypage.controller;

import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.mypage.dto.MyPageResponseDto;
import com.pj2z.pj2zbe.mypage.service.MyPageService;
import com.pj2z.pj2zbe.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "마이페이지", description = "사용자의 마이페이지에 대한 API")
public class MyPageController {
    @Autowired
    MyPageService myPageService;

    @GetMapping("/mypage")
    @Operation(summary = "닉네임 조회", description = "사용자의 닉네임 조회")
    public ResponseEntity<MyPageResponseDto> getNickname(@UserCheck User user) {
        MyPageResponseDto response = myPageService.getUserNickname(user);
        return ResponseEntity.ok(response);
    }
}
