package com.pj2z.pj2zbe.goal.dto;

import com.pj2z.pj2zbe.user.entity.UserGoalYN;
import lombok.Getter;

@Getter
public class GoalYNUpdateDto
{
    private Long userId;
    private UserGoalYN goalYN;

}
