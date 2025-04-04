package com.pj2z.pj2zbe.balanceGame.entity;

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

    public BalanceGame() {
    }

    public BalanceGame(LocalDate gameDate, String question, String optionA, String optionB) {
        this.gameDate = gameDate;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
    }


    public void updateBalanceGame(String question, String optionA, String optionB) {
         this.question = question;
         this.optionA = optionA;
         this.optionB = optionB;
    }
}
