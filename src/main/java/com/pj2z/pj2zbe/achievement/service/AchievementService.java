package com.pj2z.pj2zbe.achievement.service;

import com.pj2z.pj2zbe.achievement.repository.AchievementRepository;
import com.pj2z.pj2zbe.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AchievementService {

    private final UserRepository userRepository;
    private final AchievementRepository achievementRepository;


}
