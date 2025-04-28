package com.pj2z.pj2zbe.balanceGame.exception;

import com.pj2z.pj2zbe.common.exception.BalanceGameException;
import org.springframework.http.HttpStatus;

public class OnlyVoteTodayBalanceGameException extends BalanceGameException {
    public OnlyVoteTodayBalanceGameException() {
        super("당일 밸런스 게임만 투표 가능합니다.", HttpStatus.BAD_REQUEST);
    }
}
