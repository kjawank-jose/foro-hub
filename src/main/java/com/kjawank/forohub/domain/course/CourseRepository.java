package com.kjawank.forohub.domain.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByNombre(String nombre);
    Page<Course> findByActivoTrue(Pageable paginacion);
    Page<Course> findByActivoFalse(Pageable paginacion);
}
