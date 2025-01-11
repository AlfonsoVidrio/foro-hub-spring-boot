package com.alfonsovidrio.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTopicData(
        @NotNull
        Long id,
        String title,
        String message,
        Status status
) {
}
