package com.pj2z.pj2zbe.balanceGame.exception;


import com.pj2z.pj2zbe.common.exception.BalanceGameException;
import org.springframework.http.HttpStatus;

public class AlreadyBalanceGameVotedUserException extends BalanceGameException {
    public AlreadyBalanceGameVotedUserException() {
        super("이미 투표하셨습니다.", HttpStatus.BAD_REQUEST);
    }
}