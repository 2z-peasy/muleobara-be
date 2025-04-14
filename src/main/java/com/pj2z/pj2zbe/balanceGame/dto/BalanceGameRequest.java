package com.pj2z.pj2zbe.balanceGame.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BalanceGameRequest {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gameDate;
    private String question;
    private String optionA;
    private String optionB;


}
