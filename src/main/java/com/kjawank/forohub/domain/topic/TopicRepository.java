package com.kjawank.forohub.domain.topic;

import com.kjawank.forohub.domain.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Boolean existsByTituloAndMensaje(String tituloTopico, String mensajeTopico);
    void removeById(Long id);
    Page<Topic> findAllByCurso(Course curso, Pageable paginacion);
    Page<Topic> findAllByStatusTrue(Pageable paginacion);
    Page<Topic> findAllByStatusFalse(Pageable paginacion);
}
