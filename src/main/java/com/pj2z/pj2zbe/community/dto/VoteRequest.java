package com.pj2z.pj2zbe.community.dto;

import com.pj2z.pj2zbe.community.entity.VoteType;

public record VoteRequest(
        VoteType voteType
) {
}
