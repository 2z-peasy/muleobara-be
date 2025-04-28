package com.pj2z.pj2zbe.balanceGame.exception;

import com.pj2z.pj2zbe.common.exception.BalanceGameException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class AlreadyExistBalanceGameDateException extends BalanceGameException {
    public AlreadyExistBalanceGameDateException(LocalDate date) {
        super(date + " 날짜에 이미 밸런스 게임이 존재합니다.", HttpStatus.BAD_REQUEST);
        }
}
