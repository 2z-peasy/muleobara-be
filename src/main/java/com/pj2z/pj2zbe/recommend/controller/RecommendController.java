package com.pj2z.pj2zbe.recommend.controller;

import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.common.template.RspTemplate;
import com.pj2z.pj2zbe.recommend.dto.request.RecommendRequest;
import com.pj2z.pj2zbe.recommend.dto.response.RecommendResponse;
import com.pj2z.pj2zbe.recommend.service.RecommendService;
import com.pj2z.pj2zbe.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommends/request")
@Tag(name = "GPT 추천", description = "GPT 추천에 대한 API")
public class RecommendController {

    private final RecommendService recommendService;

    @PostMapping
    @Operation(summary = "선택 추천 받기", description = "선택 추천 받기")
    public RspTemplate<RecommendResponse> recommend(@UserCheck User user,
                                                    @RequestBody RecommendRequest request) throws IOException {
        RecommendResponse response = recommendService.getRecommendation(user, request);
        return new RspTemplate<>(HttpStatus.OK, "성공적으로 추천받았습니다.", response);
    }
}
