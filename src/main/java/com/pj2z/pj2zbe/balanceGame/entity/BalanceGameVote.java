package com.pj2z.pj2zbe.balanceGame.entity;

import com.pj2z.pj2zbe.balanceGame.entity.enums.BalanceGameVoteChoice;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@Entity
public class BalanceGameVote {

    @EmbeddedId
    private BalanceGameVoteId id;

    @Enumerated(EnumType.STRING)
    private BalanceGameVoteChoice choice;



    public BalanceGameVote() {}

    public BalanceGameVote(BalanceGameVoteId id, BalanceGameVoteChoice choice) {
        this.id = id;
        this.choice = choice;
    }
    public BalanceGameVote(Long userid, LocalDate gameDate, BalanceGameVoteChoice choice) {
        this.id = new BalanceGameVoteId(gameDate, userid);
        this.choice = choice;
    }
}
