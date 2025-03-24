package com.pj2z.pj2zbe.goal.controller;

import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.goal.dto.GoalEntityListResponseDto;
import com.pj2z.pj2zbe.goal.dto.GoalResponseDto;
import com.pj2z.pj2zbe.goal.dto.GoalUpdateDto;
import com.pj2z.pj2zbe.goal.dto.GoalYNUpdateDto;
import com.pj2z.pj2zbe.goal.service.GoalService;
import com.pj2z.pj2zbe.user.entity.User;
import com.pj2z.pj2zbe.user.entity.UserGoalYN;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goals")
@Tag(name = "목표", description = "사용자의 목표에 대한 API")
public class GoalController {

    private final GoalService goalService;

    @PostMapping("/update")
    @Operation(summary = "목표 업데이트", description = "사용자의 목표 업데이트")
    public ResponseEntity<Object> userGoalUpdate(@UserCheck User user, @RequestBody @Valid GoalUpdateDto updateDto){
        try {
            goalService.updateUserGoals(user, updateDto.getGoals());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(null) ;

        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/list")
    @Operation(summary = "목표 조회", description = "사용자의 목표 조회")
    public ResponseEntity<Object> userGoalGetList(@UserCheck User user){
        try {
            GoalResponseDto goalResponseDto = goalService.getGoalTotalDataByUserId(user);

            return ResponseEntity.ok(goalResponseDto);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", ex.getMessage()
            ));
        }
    }

    @PostMapping("/used")
    @Operation(summary = "목표 사용 여부 업데이트", description = "사용자의 목표 사용 여부 업데이트")
    public ResponseEntity<Object> userGoalYNUpdate(@UserCheck User user, @RequestBody GoalYNUpdateDto goalUsedYN){
         try {
             user.updateUserGoalYN(goalUsedYN.isGoalYN() ? UserGoalYN.Y : UserGoalYN.N);

             return ResponseEntity.ok(null);
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", ex.getMessage()
            ));
        }
    }


    @GetMapping("/active")
    @Operation(summary = "목표 조회", description = "현재 서비스중인 목표리스트 조회")
    public ResponseEntity<List<GoalEntityListResponseDto>> getActiveGoals() {
        List<GoalEntityListResponseDto> goals = goalService.getActiveGoals();
        return ResponseEntity.ok(goals);
    }
}
