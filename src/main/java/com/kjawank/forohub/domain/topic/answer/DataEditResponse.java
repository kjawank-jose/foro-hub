package com.kjawank.forohub.domain.topic.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataEditResponse(
        @NotNull
        String mensaje,
        @NotBlank
        Long idAutor
) {
}
