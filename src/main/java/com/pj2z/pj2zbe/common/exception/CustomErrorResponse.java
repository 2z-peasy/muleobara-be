package com.pj2z.pj2zbe.common.exception;

public record CustomErrorResponse(
        int status,
        String message
) {
}
