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

    private Long optionACount;
    private Long optionBCount;
    private Long voteCount;
    private Double optionARate;
    private Double optionBRate;

    public BalanceGameResponse(BalanceGame balanceGame) {
        //1. 투표를 안했을경우 과거의 밸런스게임만 투표현황보여주기.

        this.gameDate = balanceGame.getGameDate();
        this.question = balanceGame.getQuestion();
        this.optionA = balanceGame.getOptionA();
        this.optionB = balanceGame.getOptionB();

        this.choice = null;

        if(balanceGame.getGameDate().isBefore(LocalDate.now())) {
            this.optionACount = balanceGame.getOptionACount();
            this.optionBCount = balanceGame.getOptionBCount();
            this.voteCount = balanceGame.getOptionACount() + balanceGame.getOptionBCount();
            //투표율
            this.VoteRate();
        }else{
                this.optionACount = null;
                this.optionBCount = null;
                this.voteCount = null;
                this.optionARate = null;
                this.optionBRate = null;
        }


    }

    public BalanceGameResponse(BalanceGame balanceGame, BalanceGameVoteChoice voteChoice) {
        this.gameDate = balanceGame.getGameDate();
        this.question = balanceGame.getQuestion();
        this.optionA = balanceGame.getOptionA();
        this.optionB = balanceGame.getOptionB();
        this.choice = (voteChoice != null) ? voteChoice.toString() : null;

        if(this.choice != null || balanceGame.getGameDate().isBefore(LocalDate.now())) {
            this.optionACount = balanceGame.getOptionACount();
            this.optionBCount = balanceGame.getOptionBCount();
            this.voteCount = balanceGame.getOptionACount() + balanceGame.getOptionBCount();

            this.VoteRate();
        } else{
            this.optionACount = null;
            this.optionBCount = null;
            this.voteCount = null;
            this.optionARate = null;
            this.optionBRate = null;
        }
    }

    // 투표율 계산
    private void VoteRate(){
        if(this.voteCount == null){
            this.optionACount = null;
            this.optionBCount = null;
            this.optionARate = null;
            this.optionBRate = null;
            return;
        }
        if(voteCount <= 0){
            this.optionARate = 0D;
            this.optionBRate = 0D;
            return;
        }
        //소수점첫째짜리까지 반환 (2025.05.02 회의결과)
        this.optionARate = Math.round((double) this.optionACount * 1000 / this.voteCount) / 10.0;
        //this.optionBRate = Math.round((double) this.optionBCount * 1000 / this.voteCount) / 10.0;
        this.optionBRate = 100.0 - this.optionARate;
    }

}
