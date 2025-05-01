package com.pj2z.pj2zbe.balanceGame.exception;

import com.pj2z.pj2zbe.common.exception.BalanceGameException;
import org.springframework.http.HttpStatus;

public class CannotViewFutureBalanceGameException  extends BalanceGameException {
    public CannotViewFutureBalanceGameException() {
        super("미래의 밸런스 게임은 조회가 불가능합니다.", HttpStatus.BAD_REQUEST);
    }
}
