package com.pj2z.pj2zbe.goal.repository;

import com.pj2z.pj2zbe.goal.entity.UserGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserGoalRepository extends JpaRepository<UserGoal,Long> {
    Optional<List<UserGoal>> findAllByUserId(Long userId);

    Optional<UserGoal> findByUserId(Long userId);
}
