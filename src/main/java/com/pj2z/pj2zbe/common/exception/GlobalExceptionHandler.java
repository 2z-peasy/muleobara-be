package com.pj2z.pj2zbe.common.exception;

import com.pj2z.pj2zbe.community.exception.VoteDeadlineException;
import com.pj2z.pj2zbe.community.exception.VoteNotFoundException;
import com.pj2z.pj2zbe.mbti.exception.MbtiNotFoundException;
import com.pj2z.pj2zbe.recommend.exception.NoTicketsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            UserNotFoundException.class,
            TestNotFoundException.class,
            GoalNotFoundException.class,
            MbtiNotFoundException.class
    })
    public ResponseEntity<Map<String, Object>> handleNotFound(RuntimeException e) {
        log.error(e.getMessage());
        return getErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler({
            NoTicketsException.class,
            VoteDeadlineException.class,
            VoteNotFoundException.class
    })
    public ResponseEntity<Map<String, Object>> handleBadRequest(RuntimeException e) {
        log.error(e.getMessage());
        return getErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({CustomAuthenticationException.class})
    public ResponseEntity<Map<String, Object>> handleCustomAuthentication(RuntimeException e) {
        log.error(e.getMessage());
        return getErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<Map<String, Object>> handleServerException(ServerException e) {
        log.error(e.getMessage());
        return getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    public ResponseEntity<Map<String, Object>> getErrorResponse(HttpStatus status, String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", status.name());
        errorResponse.put("code", status.value());
        errorResponse.put("message", message);

        return new ResponseEntity<>(errorResponse, status);
    }
}
