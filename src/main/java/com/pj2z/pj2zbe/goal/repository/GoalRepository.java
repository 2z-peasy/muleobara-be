package com.pj2z.pj2zbe.goal.repository;

import com.pj2z.pj2zbe.goal.entity.GoalEntity;
import com.pj2z.pj2zbe.goal.entity.enums.GoalUsedYN;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoalRepository extends JpaRepository<GoalEntity,Long> {
    Optional<GoalEntity> findByGoalName(String name);
    List<GoalEntity> findByUsedYN(GoalUsedYN usedYN);
}
