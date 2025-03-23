package com.pj2z.pj2zbe.goal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pj2z.pj2zbe.user.entity.UserGoalYN;
import lombok.Getter;

@Getter
public class GoalYNUpdateDto
{
    @JsonProperty("goalYN")
    private boolean goalYN;
}
