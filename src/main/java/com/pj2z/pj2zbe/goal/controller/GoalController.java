package com.pj2z.pj2zbe.goal.controller;

import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.common.jwt.JwtUtil;
import com.pj2z.pj2zbe.goal.dto.GoalResponseDto;
import com.pj2z.pj2zbe.goal.dto.GoalUpdateDto;
import com.pj2z.pj2zbe.goal.dto.GoalYNUpdateDto;
import com.pj2z.pj2zbe.goal.service.GoalService;
import com.pj2z.pj2zbe.user.entity.User;
import com.pj2z.pj2zbe.user.entity.UserGoalYN;
import com.pj2z.pj2zbe.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;

    private final UserService userService;

    private final JwtUtil jwtUtil;

    @PostMapping("/update")
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
    public ResponseEntity<Object> userGoalYNUpdate(@UserCheck User user, @RequestBody GoalYNUpdateDto goalUsedYN){
         try {
             userService.updateUserGoalYN(user, goalUsedYN.isGoalYN() ? UserGoalYN.Y : UserGoalYN.N);

             return ResponseEntity.ok(null);
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", ex.getMessage()
            ));
        }
    }
}
