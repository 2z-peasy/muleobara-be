package com.pj2z.pj2zbe.community.dto;

import com.pj2z.pj2zbe.community.entity.Vote;

public record VoteResponse(
        Long voteId,
        String voteA,
        String voteB,
        Integer voteACount,
        Integer voteBCount,
        Double totalVoteCount,
        Double voteAPercentage,
        Double voteBPercentage
) {
    public static VoteResponse from(Vote vote) {
        Integer voteACount = vote.getVoteACount();
        Integer voteBCount = vote.getVoteBCount();
        double totalVoteCount = voteACount + voteBCount;

        double voteAPercentage = totalVoteCount > 0 ? (voteACount / totalVoteCount) * 100 : 0;
        double voteBPercentage = totalVoteCount > 0 ? (voteBCount / totalVoteCount) * 100 : 0;

        return new VoteResponse(
                vote.getId(),
                vote.getVoteA(),
                vote.getVoteB(),
                voteACount,
                voteBCount,
                totalVoteCount,
                voteAPercentage,
                voteBPercentage
        );
    }
}
