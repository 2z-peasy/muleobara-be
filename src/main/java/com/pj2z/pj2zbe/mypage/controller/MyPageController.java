package com.pj2z.pj2zbe.mypage.controller;

import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.mypage.dto.MyPageResponseDto;
import com.pj2z.pj2zbe.mypage.service.MyPageService;
import com.pj2z.pj2zbe.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPageController {
    @Autowired
    MyPageService myPageService;

    @GetMapping("/mypage")
    public ResponseEntity<MyPageResponseDto> getNickname(@UserCheck User user) {
        MyPageResponseDto response = myPageService.getUserNickname(user);
        return ResponseEntity.ok(response);
    }

}
