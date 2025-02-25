package com.pj2z.pj2zbe.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SignupRequest(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
