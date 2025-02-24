package com.pj2z.pj2zbe.common.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj2z.pj2zbe.common.exception.CustomErrorResponse;
import com.pj2z.pj2zbe.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class JwtSendErrorService {

    private final ObjectMapper objectMapper;

    public void sendErrorResponseProcess(
            HttpServletResponse response, ErrorCode errorCode, int status)
            throws IOException {
        CustomErrorResponse errorResponse = new CustomErrorResponse(errorCode.getStatus(), errorCode.getMessage());
        String body = objectMapper.writeValueAsString(errorResponse);
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(body);
    }
}
