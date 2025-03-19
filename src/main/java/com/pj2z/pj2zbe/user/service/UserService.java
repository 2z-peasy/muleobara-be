package com.pj2z.pj2zbe.user.service;

import com.pj2z.pj2zbe.user.entity.User;
import com.pj2z.pj2zbe.user.entity.UserGoalYN;
import com.pj2z.pj2zbe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void updateUserGoalYN(Long userId, UserGoalYN GoalYN) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        user.setGoalYN(GoalYN);
    }
}
