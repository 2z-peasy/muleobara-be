package com.pj2z.pj2zbe.community.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "커뮤니티 게시글 생성 요청")
public record PostCreateRequest(

        @Schema(description = "제목", example = "제목")
        @NotBlank(message = "제목은 필수 입력값입니다.")
        String title,

        @Schema(description = "내용", example = "내용")
        @NotBlank(message = "내용은 필수 입력값입니다.")
        String content,

        @Schema(description = "투표", example = "{ \"vote_a\": \"투표A\", \"vote_b\": \"투표B\", \"vote_deadline\": \"2025-03-27 00:00\" }")
        @NotNull(message = "투표는 필수 입력값입니다.")
        VoteForm voteForm
) {
}
