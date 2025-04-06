package com.pj2z.pj2zbe.community.repository;

import com.pj2z.pj2zbe.community.entity.VoteRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRecordRepository  extends JpaRepository<VoteRecord, Long> {
    boolean existsByVoteIdAndUserId(Long voteId, Long userId);
}
