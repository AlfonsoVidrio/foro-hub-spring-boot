package com.alfonsovidrio.forohub.domain.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseData(
        @NotBlank
        String name,
        String description,
        @NotNull
        Category category
) {
}
