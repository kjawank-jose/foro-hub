package com.kjawank.forohub.domain.topic.answer;

import com.kjawank.forohub.domain.topic.Topic;
import com.kjawank.forohub.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    boolean existsByTopicoAndSolucion(Topic topico,
                                      boolean solucion);
    boolean existsByTopicoAndMensajeAndAutorRespuesta(Topic topico,
                                                      String mensaje,
                                                      User autorRespuesta);
    Page<Response> findAllByTopico(Topic topico,
                                   Pageable paginacion);
    void removeAllByTopico(Topic referenceById);
}
