package com.kjawank.forohub.domain.topic;

import jakarta.validation.constraints.NotNull;

public record DataUpdateTopic(
        @NotNull
        Long id,
        String titulo,
        String mensaje
) {
}
