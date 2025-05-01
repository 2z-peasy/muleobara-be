package com.pj2z.pj2zbe.balanceGame.exception;

import com.pj2z.pj2zbe.common.exception.BalanceGameException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class BalaceGameNotFoundException extends BalanceGameException {
    public BalaceGameNotFoundException() {
        super("해당 날짜의 밸런스게임이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }
}
