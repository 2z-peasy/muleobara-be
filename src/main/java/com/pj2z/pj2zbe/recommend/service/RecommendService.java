package com.pj2z.pj2zbe.recommend.service;

import com.pj2z.pj2zbe.common.exception.UserNotFoundException;
import com.pj2z.pj2zbe.mbti.exception.MbtiNotFoundException;
import com.pj2z.pj2zbe.user.entity.User;
import com.pj2z.pj2zbe.user.repository.UserRepository;
import com.pj2z.pj2zbe.goal.entity.GoalEntity;
import com.pj2z.pj2zbe.goal.entity.UserGoalEntity;
import com.pj2z.pj2zbe.goal.repository.UserGoalRepository;
import com.pj2z.pj2zbe.mbti.entity.Mbti;
import com.pj2z.pj2zbe.mbti.repository.MbtiRepository;
import com.pj2z.pj2zbe.recommend.dto.request.ChatGPTRequest;
import com.pj2z.pj2zbe.recommend.dto.request.RecommendRequest;
import com.pj2z.pj2zbe.recommend.dto.response.ChatGPTResponse;
import com.pj2z.pj2zbe.recommend.dto.response.RecommendResponse;
import com.pj2z.pj2zbe.user.entity.UserGoalYN;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendService {

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final MbtiRepository mbtiRepository;
    private final UserGoalRepository userGoalRepository;

    @Value("${openai.api.url}")
    private String apiURL;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.prompt}")
    private String promptTemplate;

    public RecommendResponse getRecommendation(RecommendRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Mbti mbti = mbtiRepository.findTopByUserOrderByCreatedAtDesc(user)
                .orElseThrow(() -> new MbtiNotFoundException("mbti not found"));

        List<String> goalNames = fetchUserGoals(user.getUserGoalYN(), request.userId());

        String prompt = createPrompt(request, mbti, goalNames);
        ChatGPTResponse gptResponse = sendRequestToGPT(prompt);

        return formatGPTResponse(gptResponse);
    }

    private List<String> fetchUserGoals(UserGoalYN goalChoice, Long userId) {
        if (goalChoice == UserGoalYN.N) {
            return null;
        }
        return userGoalRepository.findAllByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("No goals found for user"))
                .stream()
                .map(UserGoalEntity::getGoal)
                .map(GoalEntity::getGoalName)
                .toList();
    }

    private String createPrompt(RecommendRequest request, Mbti mbti, List<String> goalNames) {
        String retryPrompt = Optional.ofNullable(request.retry()).orElse("");
        String setting = Optional.ofNullable(request.setting()).orElse("");
        String goals = (goalNames == null || goalNames.isEmpty()) ? "" : String.join(", ", goalNames) ;
        String choices = String.join(", ", request.choices());

        return putValuesInPrompt(retryPrompt, choices, setting, goals, mbti);
    }

    private String putValuesInPrompt(String retry, String choices, String setting, String goals, Mbti mbti) {
        Map<String, String> values = new HashMap<>();
        values.put("retry", retry);
        values.put("choices", choices);
        values.put("setting", setting);
        values.put("goals", goals);
        values.put("mbti", mbti.getMbtiType().name());
        values.put("epercent", String.valueOf(mbti.getEPercent()));
        values.put("ipercent", String.valueOf(mbti.getIPercent()));
        values.put("npercent", String.valueOf(mbti.getNPercent()));
        values.put("spercent", String.valueOf(mbti.getSPercent()));
        values.put("tpercent", String.valueOf(mbti.getTPercent()));
        values.put("fpercent", String.valueOf(mbti.getFPercent()));
        values.put("jpercent", String.valueOf(mbti.getJPercent()));
        values.put("ppercent", String.valueOf(mbti.getPPercent()));

        for (Map.Entry<String, String> entry : values.entrySet()) {
            promptTemplate = promptTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return promptTemplate;
    }

    private ChatGPTResponse sendRequestToGPT(String prompt) {
        log.info("GPT한테 보내는 prompt 내용: {}", prompt);
        ChatGPTRequest chatGPTRequest = ChatGPTRequest.fromPrompt(model, prompt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ChatGPTRequest> requestEntity = new HttpEntity<>(chatGPTRequest, headers);
        ResponseEntity<ChatGPTResponse> response = restTemplate.postForEntity(apiURL, requestEntity, ChatGPTResponse.class);

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new RuntimeException("GPT API 응답받기를 실패했습니다.");
        }
        return response.getBody();
    }

    private RecommendResponse formatGPTResponse(ChatGPTResponse gptResponse) {
        String content = gptResponse.choices().get(0).message().content();
        String[] splitContent = content.split("\n", 2);

        String suggestion = splitContent[0].trim();
        String reason = splitContent.length > 1 ? splitContent[1].trim() : "";

        return new RecommendResponse(suggestion, reason);
    }
}
