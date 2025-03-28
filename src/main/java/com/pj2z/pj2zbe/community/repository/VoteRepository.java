package com.pj2z.pj2zbe.community.repository;

import com.pj2z.pj2zbe.community.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
