package com.pj2z.pj2zbe.goal.dto;

import com.pj2z.pj2zbe.auth.entity.UserGoalYN;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GoalResponseDto {
    List<String> goals ;
    UserGoalYN goalYN ;

}
