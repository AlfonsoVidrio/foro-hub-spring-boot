package com.alfonsovidrio.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicData(
        @NotBlank
        String title,
        String message,
        Status status,
        @NotNull
        Long userId,
        @NotNull
        Long courseId
) {
}
