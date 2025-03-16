package com.pj2z.pj2zbe.recommend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RecommendRequest(
        @NotNull
        Long userId,
        String setting,
        @NotBlank
        List<String> choices,
        String retry
) {
}
