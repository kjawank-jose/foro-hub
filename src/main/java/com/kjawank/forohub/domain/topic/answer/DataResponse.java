package com.kjawank.forohub.domain.topic.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataResponse(
        @NotNull
        Long id,
        @NotBlank
        String mensaje,
        @NotBlank
        Long idTopico,
        @NotNull
        Long idAutor,

        Boolean solucionado
) {
    public DataResponse(Response response) {
        this(response.getId(),
                response.getMensaje(),
                response.getTopico().getId(),
                response.getAutorRespuesta().getId(),
                response.getSolucion());
    }
}
