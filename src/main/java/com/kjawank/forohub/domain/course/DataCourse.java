package com.kjawank.forohub.domain.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataCourse(
        @NotBlank
        Long id,
        @NotBlank
        String nombre,
        @NotBlank
        Category categoria,
        @NotNull
        Boolean activo
) {
    public DataCourse(Course curso){
        this(curso.getId(),
                curso.getNombre(),
                curso.getCategoria(),
                curso.getActivo());
    }
}
