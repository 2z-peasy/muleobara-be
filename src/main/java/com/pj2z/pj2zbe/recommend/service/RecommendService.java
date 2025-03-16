package com.pj2z.pj2zbe.recommend.service;

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

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendService {

    private static final String RETRY_SUFFIX = "=> 다만 이 선택지는 제외하고 추천해주세요.";

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
                .orElseThrow(() -> new RuntimeException("User not found"));

        Mbti mbti = mbtiRepository.findTopByUserOrderByCreatedAtDesc(user)
                .orElseThrow(() -> new RuntimeException("mbti not found"));

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
        String retryPrompt = Optional.ofNullable(request.retry())
                .map(r -> r + RETRY_SUFFIX)
                .orElse("");
        String setting = Optional.ofNullable(request.setting()).orElse("");
        String goals = (goalNames == null || goalNames.isEmpty()) ? "" : String.join(", ", goalNames) ;
        String choices = String.join(", ", request.choices());

        return String.format(
                promptTemplate,
                retryPrompt,
                choices,
                setting,
                goals,
                mbti.getMbtiType()
        );
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
