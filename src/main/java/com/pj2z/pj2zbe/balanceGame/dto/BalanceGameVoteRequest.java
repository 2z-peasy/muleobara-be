package com.pj2z.pj2zbe.balanceGame.dto;

import com.pj2z.pj2zbe.balanceGame.entity.enums.BalanceGameVoteChoice;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BalanceGameVoteRequest {

    private LocalDate gameDate;
    private BalanceGameVoteChoice choice;

    public BalanceGameVoteRequest() {
    }

    public BalanceGameVoteRequest(LocalDate gameDate, BalanceGameVoteChoice choice) {
        this.gameDate = gameDate;
        this.choice = choice;
    }
}
