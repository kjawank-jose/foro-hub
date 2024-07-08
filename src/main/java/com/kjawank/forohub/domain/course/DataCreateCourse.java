package com.kjawank.forohub.domain.course;

import jakarta.validation.constraints.NotNull;

public record DataCreateCourse(
        @NotNull
        String nombre,
        @NotNull
        Category categoria
) {
}
