package com.pj2z.pj2zbe.community.dto;

import com.pj2z.pj2zbe.community.entity.Post;
import com.pj2z.pj2zbe.community.entity.Vote;
import lombok.Builder;

@Builder
public record PostResponse(
        Long id,
        String title,
        String content,
        Long authorId,
        Long likeCount,
        VoteResponse voteResponse
) {
    public static PostResponse from(Post post, Vote vote) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorId(post.getAuthorId())
                .likeCount(post.getLikeCount())
                .voteResponse(com.pj2z.pj2zbe.community.dto.VoteResponse.from(vote))
                .build();
    }
}
