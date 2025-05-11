package com.pj2z.pj2zbe.community.repository;

import com.pj2z.pj2zbe.community.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByPostId(Long postId);
}
