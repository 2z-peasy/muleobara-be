package com.pj2z.pj2zbe.mypage.service;

import com.pj2z.pj2zbe.mypage.dto.MyPageResponseDto;
import com.pj2z.pj2zbe.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {

    public MyPageResponseDto getUserNickname(User user) {
        // 닉네임 반환
        return MyPageResponseDto.builder()
                .nickname(user.getNickname())
                .build();
    }
}
