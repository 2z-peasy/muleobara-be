package com.pj2z.pj2zbe.balanceGame.entity;

import com.pj2z.pj2zbe.balanceGame.entity.enums.BalanceGameVoteChoice;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class BalanceGame {
    @Id
    private LocalDate gameDate; // 오늘의 날짜가 PK

    private String question;
    private String optionA;
    private String optionB;

    private Long optionACount;
    private Long optionBCount;

    public BalanceGame() {
    }

    public BalanceGame(LocalDate gameDate, String question, String optionA, String optionB) {
        this.gameDate = gameDate;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionACount = 0L;
        this.optionBCount = 0L;
    }


    /*
     entity 메소드
     */
    public void updateBalanceGame(String question, String optionA, String optionB) {
         this.question = question;
         this.optionA = optionA;
         this.optionB = optionB;
    }

    public void incrementOptionCount(BalanceGameVoteChoice vote) {
        if(vote == BalanceGameVoteChoice.A) {
            optionACount++;
        }else if(vote == BalanceGameVoteChoice.B) {
            optionBCount++;
        }
    }

}
