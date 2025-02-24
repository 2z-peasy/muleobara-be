package com.pj2z.pj2zbe.recommend.dto.response;

import com.pj2z.pj2zbe.recommend.dto.request.Message;

import java.util.List;

public record ChatGPTResponse(
        List<Choice> choices,
        Usage usage
) {
    public record Choice(
            Message message
    ) {
    }

    public record Usage(
            int prompt_tokens,
            int completion_tokens,
            int total_tokens
    ) {
    }
}
