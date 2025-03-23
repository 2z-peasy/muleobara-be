package com.pj2z.pj2zbe.recommend.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record RecommendRequest(
        String setting,
        @NotBlank
        List<String> choices,
        String retry
) {
}
