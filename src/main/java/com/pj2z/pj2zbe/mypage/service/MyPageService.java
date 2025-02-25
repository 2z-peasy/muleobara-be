package com.pj2z.pj2zbe.mypage.service;

import com.pj2z.pj2zbe.mypage.dto.MyPageResponseDto;
import com.pj2z.pj2zbe.user.entity.UserEntity;
import com.pj2z.pj2zbe.user.repository.UserOriginalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {
    @Autowired
    UserOriginalRepository userOriginalRepository;

    public MyPageResponseDto getUserNickname(Long userId) {
        // 로그인된 사용자를 ID로 조회
        UserEntity user = userOriginalRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // 닉네임 반환
        return MyPageResponseDto.builder()
                .nickname(user.getNickname())
                .build();
    }
}
