package com.pj2z.pj2zbe.achievement.service;

import com.pj2z.pj2zbe.achievement.entity.Achievement;
import com.pj2z.pj2zbe.achievement.repository.AchievementRepository;
import com.pj2z.pj2zbe.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AchievementService {

    private final AchievementRepository achievementRepository;

    public Long getLikeCount(User user) {
        return this.findByUser(user).getLikeCount();
    }

    public Long addLikeConut(User user){
        Achievement achievement =  this.findByUser(user);
        achievement.addLikecount();
        return achievementRepository.save(achievement).getLikeCount();
    }

    public Achievement findByUser(User user) {
         return achievementRepository.findByUser(user)
                 .orElseGet(()->new Achievement(user));
    }
}
