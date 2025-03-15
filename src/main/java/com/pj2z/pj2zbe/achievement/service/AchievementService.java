package com.pj2z.pj2zbe.achievement.service;

import com.pj2z.pj2zbe.achievement.entity.Achievement;
import com.pj2z.pj2zbe.achievement.repository.AchievementRepository;
import com.pj2z.pj2zbe.auth.entity.User;
import com.pj2z.pj2zbe.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AchievementService {

    private final UserRepository userRepository;
    private final AchievementRepository achievementRepository;

    public Long getLikeCount(Long userId) {
        return this.findById(userId).getLikeCount();
    }

    public Long addLikeConut(Long userId){
        Achievement achievement =  this.findById(userId);
        achievement.addLikecount();
        return achievementRepository.save(achievement).getLikeCount();
    }

    public Achievement findById(Long userId) {
         User user = userRepository.findById(userId)
                 .orElseThrow(() -> new RuntimeException("User not found"));

         return achievementRepository.findByUser(user)
                 .orElseGet(()->new Achievement(user));
    }


}
