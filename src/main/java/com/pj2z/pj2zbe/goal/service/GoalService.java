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

        Optional<List<UserGoal>> optionalGoals = userGoalRepository.findAllByUserId(userId);

        List<UserGoal> existingGoals = optionalGoals.orElse(new ArrayList<>());

        // 2. 기존 목표 중에 삭제해야 할 목표를 찾기
        Set<String> goalNamesSet = new HashSet<>(goalNames); // 새로 온 목표 목록
        Iterator<UserGoal> iterator = existingGoals.iterator();
        while (iterator.hasNext()) {
            UserGoal userGoal = iterator.next();
            String existingGoalName = userGoal.getGoal().getGoalName(); // GoalEntity의 이름
            if (!goalNamesSet.contains(existingGoalName)) {
                // 새로 온 목표 목록에 없으면 삭제
                userGoalRepository.delete(userGoal);
                iterator.remove();
            }
        }

        // 3. 새로 추가해야 할 목표를 찾아 추가
        for (String goalName : goalNames) {
            boolean isGoalExist = existingGoals.stream()
                    .anyMatch(userGoal -> userGoal.getGoal().getGoalName().equals(goalName));

            if (!isGoalExist) {
                // goalName에 해당하는 GoalEntity를 찾아서 추가
                GoalEntity goalEntity = goalRepository.findByGoalName(goalName)
                        .orElseThrow(() -> new IllegalArgumentException("Goal not found: " + goalName));
                if(goalEntity.getUsedYN() != GoalUsedYN.N) {
                    UserGoal userGoal = UserGoal.builder()
                            .user(user)
                            .goal(goalEntity)
                            .build();

                    userGoalRepository.save(userGoal); // 새로운 UserGoalEntity 저장
                }
            }
        }
    }

    public GoalResponseDto getGoalTotalDataByUserId(Long userId) {
        // usergoal 테이블에서 userId에 해당하는 모든 UserGoalEntity를 조회
        List<String> goals = new ArrayList<>();

        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Optional<List<UserGoal>> optionalGoals = userGoalRepository.findAllByUserId(userId);

        List<UserGoal> userGoals = optionalGoals.orElse(new ArrayList<>());
        if (!userGoals.isEmpty()) {
            for (UserGoal userGoal : userGoals) {
                if(userGoal.getGoal().getUsedYN() != GoalUsedYN.N) { //자연스럽게 사용안하는 목표는 제거되도록
                    goals.add(userGoal.getGoal().getGoalName());
                }
            }
        }
        return new GoalResponseDto(goals,user.getUserGoalYN());

    }


}
