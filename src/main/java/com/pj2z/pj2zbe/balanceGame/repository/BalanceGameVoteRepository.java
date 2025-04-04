package com.pj2z.pj2zbe.balanceGame.repository;

import com.pj2z.pj2zbe.balanceGame.entity.BalanceGameVote;
import com.pj2z.pj2zbe.balanceGame.entity.BalanceGameVoteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceGameVoteRepository extends JpaRepository<BalanceGameVote, BalanceGameVoteId> {

}
