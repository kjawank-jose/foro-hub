package com.kjawank.forohub.domain.topic;

import java.time.LocalDateTime;

public record DataTopic(Long id,
                        String titulo,
                        String mensaje,
                        LocalDateTime fecha,
                        Boolean status,
                        String autor,
                        String curso) {

    public DataTopic(Topic topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutorTopico().getNombre(),
                topico.getCurso().getNombre());
    }
}
