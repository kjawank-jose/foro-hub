package com.kjawank.forohub.domain.topic;

import jakarta.validation.constraints.NotNull;

public record DataCreateTopic(
        @NotNull
        Long idAutor,

        @NotNull
        Long idCurso,

        @NotNull
        String titulo,

        @NotNull
        String mensaje
) {
}
