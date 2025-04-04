package com.pj2z.pj2zbe.balanceGame.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BalanceGameRequest {
    private LocalDate gameDate;
    private String question;
    private String optionA;
    private String optionB;
}
