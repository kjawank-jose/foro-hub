package com.kjawank.forohub.domain.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateCourse(
        @NotNull
        Long id,
        @NotBlank
        String nombre,
        @NotBlank
        Category categoria
) {
}
