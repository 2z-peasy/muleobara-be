package com.pj2z.pj2zbe.community.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class VoteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long voteId;
    private Long postId;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private VoteType voteType;    // A or B ...

    @Builder
    public VoteRecord(Long voteId, Long postId, Long userId, VoteType voteType) {
        this.voteId = voteId;
        this.postId = postId;
        this.userId = userId;
        this.voteType = voteType;
    }
}
