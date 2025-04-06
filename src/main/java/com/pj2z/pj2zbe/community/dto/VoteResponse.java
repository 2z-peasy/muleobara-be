package com.pj2z.pj2zbe.community.dto;

public record VoteResponse(
        Long voteId,
        Integer voteACount,
        Integer voteBCount,
        Double totalVoteCount,
        Double voteAPercentage,
        Double voteBPercentage
) {
}
