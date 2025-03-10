package com.pj2z.pj2zbe.mypage.service;

import com.pj2z.pj2zbe.auth.entity.User;
import com.pj2z.pj2zbe.auth.repository.UserRepository;
import com.pj2z.pj2zbe.mypage.dto.MyPageResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {
    @Autowired
    UserRepository userRepository;

    public MyPageResponseDto getUserNickname(Long userId) {
        // 로그인된 사용자를 ID로 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // 닉네임 반환
        return MyPageResponseDto.builder()
                .nickname(user.getNickname())
                .build();
    }
}
