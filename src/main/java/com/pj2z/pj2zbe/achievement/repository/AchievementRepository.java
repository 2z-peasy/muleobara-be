package com.pj2z.pj2zbe.achievement.repository;

import com.pj2z.pj2zbe.achievement.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
