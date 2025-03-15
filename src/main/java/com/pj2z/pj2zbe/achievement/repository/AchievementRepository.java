package com.pj2z.pj2zbe.achievement.repository;

import com.pj2z.pj2zbe.achievement.entity.Achievement;
import com.pj2z.pj2zbe.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    Optional<Achievement> findByUser(User user);
}
