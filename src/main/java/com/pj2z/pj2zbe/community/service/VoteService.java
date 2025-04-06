package com.pj2z.pj2zbe.community.service;

import com.pj2z.pj2zbe.community.dto.VoteRequest;
import com.pj2z.pj2zbe.community.dto.VoteResponse;
import com.pj2z.pj2zbe.community.entity.Vote;
import com.pj2z.pj2zbe.community.entity.VoteRecord;
import com.pj2z.pj2zbe.community.entity.VoteType;
import com.pj2z.pj2zbe.community.exception.VoteNotFoundException;
import com.pj2z.pj2zbe.community.exception.VoteTypeException;
import com.pj2z.pj2zbe.community.repository.VoteRecordRepository;
import com.pj2z.pj2zbe.community.repository.VoteRepository;
import com.pj2z.pj2zbe.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final VoteRecordRepository voteRecordRepository;

    @Transactional
    public VoteResponse updateVoteCount(User user, Long voteId, VoteRequest voteRequest) {
        checkVoteRecordExists(user.getId(), voteId);

        Vote vote = addVoteAndReturn(voteId, voteRequest);
        voteRepository.save(vote);

        createVoteRecord(user, vote, voteRequest.voteType());

        double totalVoteCount = vote.getVoteACount() + vote.getVoteBCount();
        return new VoteResponse(
                voteId,
                vote.getVoteACount(),
                vote.getVoteBCount(),
                totalVoteCount,
                vote.getVoteACount() / totalVoteCount * 100,
                vote.getVoteBCount() / totalVoteCount * 100
        );
    }

    private void checkVoteRecordExists(Long userId, Long voteId) {
        if (voteRecordRepository.existsByVoteIdAndUserId(voteId, userId)) {
            throw new VoteNotFoundException("이미 투표한 사용자입니다.");
        }
    }

    private Vote addVoteAndReturn(Long voteId, VoteRequest voteRequest) {
        Vote vote = voteRepository.findById(voteId)
                .orElseThrow(() -> new VoteNotFoundException("해당 투표가 존재하지 않습니다."));

        switch (voteRequest.voteType()) {
            case A -> vote.updateAVoteCount();
            case B -> vote.updateBVoteCount();
            default -> throw new VoteTypeException("올바르지 않은 투표 타입입니다: " + voteRequest.voteType());
        }
        return vote;
    }

    private void createVoteRecord(User user, Vote vote, VoteType voteType) {
        VoteRecord voteRecord = VoteRecord.builder()
                .voteId(vote.getId())
                .postId(vote.getPostId())
                .userId(user.getId())
                .voteType(voteType)
                .build();
        voteRecordRepository.save(voteRecord);
    }
}
