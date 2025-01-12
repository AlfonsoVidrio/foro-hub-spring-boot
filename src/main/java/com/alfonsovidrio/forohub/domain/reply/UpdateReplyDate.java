package com.alfonsovidrio.forohub.domain.reply;

import jakarta.validation.constraints.NotNull;

public record UpdateReplyDate(
        @NotNull
        Long id,
        String message
) {
}
