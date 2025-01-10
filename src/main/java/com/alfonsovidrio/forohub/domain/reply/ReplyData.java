package com.alfonsovidrio.forohub.domain.reply;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReplyData(
        @NotBlank
        String message,
        LocalDateTime creationDate,
        @NotNull
        Long userId,
        @NotNull
        Long topicId
) {
}
