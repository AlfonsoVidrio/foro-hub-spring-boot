package com.alfonsovidrio.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record TopicData(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long userId,
        @NotNull
        Long courseId
) {
}
