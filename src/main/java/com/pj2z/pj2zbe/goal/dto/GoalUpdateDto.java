package com.pj2z.pj2zbe.goal.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class GoalUpdateDto {

    private List<String> goals;

}
