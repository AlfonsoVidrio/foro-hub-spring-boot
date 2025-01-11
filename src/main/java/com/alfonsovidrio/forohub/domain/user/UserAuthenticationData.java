package com.alfonsovidrio.forohub.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserData(
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
