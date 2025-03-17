package com.pj2z.pj2zbe.goal.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;

    private final UserService userService;


    private final JwtUtil jwtUtil;

    @PostMapping("/update")
    public ResponseEntity<Object> userGoalUpdate(@RequestHeader("Authorization") String token,@RequestBody @Valid GoalUpdateDto updateDto){

        token = token.replace("Bearer ", "");

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "로그인 정보가 유효하지 않습니다."));
        }
        Long userId = Long.valueOf(jwtUtil.getUsernameFromToken(token));

        try {
            goalService.updateUserGoals(userId, updateDto.getGoals());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(null) ;
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", ex.getMessage()));
        }

    }


    @GetMapping("/list")
    public ResponseEntity<Object> userGoalGetList(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer ", "");

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "로그인 정보가 유효하지 않습니다."));
        }
        Long userId = Long.valueOf(jwtUtil.getUsernameFromToken(token));

        try {
            GoalResponseDto goalResponseDto = goalService.getGoalTotalDataByUserId(userId);

            return ResponseEntity.ok(goalResponseDto);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", ex.getMessage()
            ));
        }

    }


    @PostMapping("/used")
    public ResponseEntity<Object> userGoalYNUpdate(@RequestHeader("Authorization") String token, @RequestBody GoalYNUpdateDto goalUsedYN){
        token = token.replace("Bearer ", "");

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "로그인 정보가 유효하지 않습니다."));
        }
        Long userId = Long.valueOf(jwtUtil.getUsernameFromToken(token));

         try {
             userService.updateUserGoalYN(userId, goalUsedYN.isGoalYN() ? UserGoalYN.Y : UserGoalYN.N);

             return ResponseEntity.ok(null);
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", ex.getMessage()
            ));
        }
    }
}
