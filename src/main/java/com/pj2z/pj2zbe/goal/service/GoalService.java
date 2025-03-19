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

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    private final UserGoalRepository  userGoalRepository;

    private final UserRepository userRepository;

    public void updateUserGoals(Long userId, List<String> goalNames) {
        // 1. 사용자와 관련된 기존 목표 목록을 가져옴
        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        List<UserGoal> existingGoals = userGoalRepository.findAllByUserId(userId).orElse(new ArrayList<>());

        // 2. 기존 목표 중에 삭제해야 할 목표를 찾기
        this.DeleteUserNotExistGoals(user,goalNames,existingGoals);

        // 3. 새로 추가해야 할 목표를 찾아 추가
        this.insertUserGoals(user,goalNames,existingGoals);
    }

    public void DeleteUserNotExistGoals(User user, List<String> goalNames) {
        List<UserGoal> existingGoals = userGoalRepository.findAllByUserId(user.getId()).orElse(new ArrayList<>());
        this.DeleteUserNotExistGoals(user,goalNames,existingGoals);
    }

    public void DeleteUserNotExistGoals(User user, List<String> goalNames,List<UserGoal> existingGoals) {
        Set<String> goalNamesSet = new HashSet<>(goalNames);
        Iterator<UserGoal> iterator = existingGoals.iterator();
        while (iterator.hasNext()) {
            UserGoal userGoal = iterator.next();
            if(!user.getId().equals(userGoal.getUser().getId())) {
                continue;
            }
            String existingGoalName = userGoal.getGoal().getGoalName();
            if (!goalNamesSet.contains(existingGoalName)) {
                // 새로 온 목표 목록에 없으면 삭제
                userGoalRepository.delete(userGoal);
                iterator.remove();
            }
        }
    }

    //User로 한이유는 검증을 거치고 온 경우만 중복검증하기에 너무 과하다 판단
    public void insertUserGoals(User user, List<String> goalNames){
        List<UserGoal> existingGoals = userGoalRepository.findAllByUserId(user.getId()).orElse(new ArrayList<>());
        this.insertUserGoals(user,goalNames,existingGoals);
    }
    public void insertUserGoals(User user, List<String> goalNames,List<UserGoal> existingGoals){
        for (String goalName : goalNames) {
            boolean isGoalExist = existingGoals.stream()
                    .anyMatch(userGoal -> userGoal.getGoal().getGoalName().equals(goalName));

            if (!isGoalExist) {
                GoalEntity goalEntity = goalRepository.findByGoalName(goalName)
                        .orElseThrow(() -> new IllegalArgumentException("Goal not found: " + goalName));
                if(goalEntity.getUsedYN() != GoalUsedYN.N) {
                    UserGoal userGoal = UserGoal.builder()
                            .user(user)
                            .goal(goalEntity)
                            .build();

                    userGoalRepository.save(userGoal);
                }
            }
        }
    }

    public GoalResponseDto getGoalTotalDataByUserId(Long userId) {
        List<String> goals = new ArrayList<>();

        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Optional<List<UserGoal>> optionalGoals = userGoalRepository.findAllByUserId(userId);

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
