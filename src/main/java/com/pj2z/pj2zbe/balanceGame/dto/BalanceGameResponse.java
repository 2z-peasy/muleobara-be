package com.pj2z.pj2zbe.balanceGame.dto;

import com.pj2z.pj2zbe.balanceGame.entity.BalanceGame;
import com.pj2z.pj2zbe.balanceGame.entity.enums.BalanceGameVoteChoice;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BalanceGameResponse {
    private LocalDate gameDate;
    private String question;
    private String optionA;
    private String optionB;

    private String choice;

    public BalanceGameResponse(BalanceGame balanceGame) {
        this.gameDate = balanceGame.getGameDate();
        this.question = balanceGame.getQuestion();
        this.optionA = balanceGame.getOptionA();
        this.optionB = balanceGame.getOptionB();

        this.choice = null;

    }

    public BalanceGameResponse(BalanceGame balanceGame, BalanceGameVoteChoice voteChoice) {
        this.gameDate = balanceGame.getGameDate();
        this.question = balanceGame.getQuestion();
        this.optionA = balanceGame.getOptionA();
        this.optionB = balanceGame.getOptionB();
        this.choice = (voteChoice != null) ? voteChoice.toString() : null;
    }


}
