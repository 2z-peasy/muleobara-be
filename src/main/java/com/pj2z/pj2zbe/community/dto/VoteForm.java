package com.pj2z.pj2zbe.community.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Schema(description = "투표 생성 폼")
public record VoteForm(

        @Schema(description = "투표A", example = "투표A")
        @NotBlank(message = "투표A는 필수 입력값입니다.")
        String voteA,

        @Schema(description = "투표B", example = "투표B")
        @NotBlank(message = "투표B는 필수 입력값입니다.")
        String voteB,

        // null이 들어올 시 마감일 자동 설정(1주일 뒤 마감)
        @Schema(description = "투표 마감일", example = "2025-03-27 00:00")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime voteDeadline
) {
}
