package com.pj2z.pj2zbe.community.entity;

import com.pj2z.pj2zbe.common.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Vote extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    private String voteA;
    private Integer voteACount = 0;

    private String voteB;
    private Integer voteBCount = 0;

    private LocalDateTime voteDeadline;

    public Vote(Long postId, String voteA, String voteB, LocalDateTime voteDeadline) {
        this.postId = postId;
        this.voteA = voteA;
        this.voteB = voteB;
        this.voteDeadline = voteDeadline!= null ? voteDeadline : LocalDateTime.now().plusDays(7);
    }

    public void updateAVoteCount(){
        voteACount++;
    }

    public void updateBVoteCount(){
        voteBCount++;
    }
}
