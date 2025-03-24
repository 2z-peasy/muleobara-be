package com.pj2z.pj2zbe.goal.service;

import com.pj2z.pj2zbe.goal.dto.GoalResponseDto;
import com.pj2z.pj2zbe.goal.entity.UserGoal;
import com.pj2z.pj2zbe.goal.entity.enums.GoalUsedYN;
import com.pj2z.pj2zbe.user.entity.User;
import com.pj2z.pj2zbe.user.repository.UserRepository;
import com.pj2z.pj2zbe.goal.entity.GoalEntity;
import com.pj2z.pj2zbe.goal.repository.GoalRepository;
import com.pj2z.pj2zbe.goal.repository.UserGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    private final UserGoalRepository  userGoalRepository;

    private final UserRepository userRepository;

    public void updateUserGoals(User user, List<Long> goalIds) {
        // 1. 사용자와 관련된 기존 목표 목록을 가져옴

        List<UserGoal> existingGoals = userGoalRepository.findAllByUserId(user.getId()).orElse(new ArrayList<>());

        // 2. 기존 목표 중에 삭제해야 할 목표를 찾기
        this.DeleteUserNotExistGoals(user,goalIds,existingGoals);

        // 3. 새로 추가해야 할 목표를 찾아 추가
        this.insertUserGoals(user,goalIds,existingGoals);
    }

    public void DeleteUserNotExistGoals(User user, List<Long> goalIds) {
        List<UserGoal> existingGoals = userGoalRepository.findAllByUserId(user.getId()).orElse(new ArrayList<>());
        this.DeleteUserNotExistGoals(user,goalIds,existingGoals);
    }

    public void DeleteUserNotExistGoals(User user, List<Long> goalIds,List<UserGoal> existingGoals) {
        Set<Long> goalIdSet = new HashSet<>(goalIds);

        // 삭제해야 할 목표 목록을 한 번에 찾기
        List<UserGoal> goalsToDelete = existingGoals.stream()
                .filter(userGoal -> user.getId().equals(userGoal.getUser().getId()))
                .filter(userGoal -> !goalIdSet.contains(userGoal.getGoal().getId())) // 새 목표 목록에 없는 경우
                .toList();

        // 한 번의 deleteAll 호출로 삭제
        userGoalRepository.deleteAll(goalsToDelete);
    }


    //User로 한이유는 검증을 거치고 온 경우만 중복검증하기에 너무 과하다 판단
    public void insertUserGoals(User user, List<Long> goalIds){
        List<UserGoal> existingGoals = userGoalRepository.findAllByUserId(user.getId()).orElse(new ArrayList<>());
        this.insertUserGoals(user,goalIds,existingGoals);
    }
    public void insertUserGoals(User user, List<Long> goalIds,List<UserGoal> existingGoals){
        Set<Long> existingGoalIds = existingGoals.stream()
                .map(userGoal -> userGoal.getGoal().getId())
                .collect(Collectors.toSet());

        List<UserGoal> userGoalsToInsert = goalIds.stream()
                .filter(goalId -> !existingGoalIds.contains(goalId)) // 기존에 없는 것만
                .map(goalId -> {
                    GoalEntity goalEntity = goalRepository.findById(goalId)
                            .orElseThrow(() -> new IllegalArgumentException("Goal not found: " + goalId));
                    return UserGoal.builder()
                            .user(user)
                            .goal(goalEntity)
                            .build();
                })
                .filter(goalEntity -> goalEntity.getGoal().getUsedYN() == GoalUsedYN.Y)
                .toList();

        userGoalRepository.saveAll(userGoalsToInsert); // 한 번에 저장
    }

    public GoalResponseDto getGoalTotalDataByUserId(User user) {
        List<String> goals = new ArrayList<>();

        Optional<List<UserGoal>> optionalGoals = userGoalRepository.findAllByUserId(user.getId());

        List<UserGoal> userGoals = optionalGoals.orElse(new ArrayList<>());
        if (!userGoals.isEmpty()) {
            for (UserGoal userGoal : userGoals) {
                if(userGoal.getGoal().getUsedYN() != GoalUsedYN.N) {
                    goals.add(userGoal.getGoal().getGoalName());
                }
            }
        }
        return new GoalResponseDto(goals,user.getUserGoalYN());

    }


}
