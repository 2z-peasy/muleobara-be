package com.pj2z.pj2zbe.mbti.service;

import com.pj2z.pj2zbe.auth.entity.User;
import com.pj2z.pj2zbe.auth.repository.UserRepository;
import com.pj2z.pj2zbe.mbti.dto.MbtiMakeRequest;
import com.pj2z.pj2zbe.mbti.entity.Mbti;
import com.pj2z.pj2zbe.mbti.repository.MbtiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MbtiService {

    @Autowired
    MbtiRepository mbtiRepository;

    @Autowired
    UserRepository userRepository;

    public void saveMbti(Long userId, MbtiMakeRequest mbti) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        mbtiRepository.save(mbti.toEntity(user));
    }

    public Mbti selectUsersMbti(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Mbti mbti = mbtiRepository.findTopByUserOrderByCreatedAtDesc(user)
                .orElseThrow(() -> new RuntimeException("mbti not found"));

        return mbti;
    }
}
