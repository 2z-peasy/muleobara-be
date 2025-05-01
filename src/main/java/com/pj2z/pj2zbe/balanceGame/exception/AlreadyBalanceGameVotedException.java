package com.pj2z.pj2zbe.balanceGame.exception;

import com.pj2z.pj2zbe.common.exception.BalanceGameException;
import org.springframework.http.HttpStatus;

public class AlreadyBalanceGameVotedException  extends BalanceGameException {
    public AlreadyBalanceGameVotedException() {
        super("이미 투표한 유저가 존재합니다.", HttpStatus.BAD_REQUEST);
    }
}
