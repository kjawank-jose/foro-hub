package com.kjawank.forohub.controller;

import com.kjawank.forohub.domain.course.CourseService;
import com.kjawank.forohub.domain.course.DataUpdateCourse;
import com.kjawank.forohub.domain.course.DataCreateCourse;
import com.kjawank.forohub.domain.course.DataCourse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping
    @Transactional
    public ResponseEntity crearCurso(
            @RequestBody
            @Valid
            DataCreateCourse datos){
        var response = service.crearCurso(datos);
        return ResponseEntity.ok(response);
    }
    @PutMapping
    @Transactional
    public ResponseEntity actualizarCurso(
            @RequestBody
            @Valid
            DataUpdateCourse datos){
        var response = service.actualizarCurso(datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity cambiarEstadoCurso(
            @PathVariable
            Long id){
        service.cambiarEstado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activos")
    public ResponseEntity<Page<DataCourse>> listarCursos(
            @PageableDefault(size=10)
            Pageable paginacion){
        var response = service.listarCursoActivos(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/inactivos")
    public ResponseEntity<Page<DataCourse>> listarCursosInactivos(
            @PageableDefault(size=10)
            Pageable paginacion){
                var response = service.listarCursosInactivos(paginacion);
                return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DataCourse>> listarCurso(@PageableDefault(size = 10) Pageable paginacion){
        var response = service.listarCursos(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity mostrarCurso(
            @PageableDefault(size = 10)
            Pageable paginacion,
            @PathVariable
            @Valid
            Long id){
        var response = service.mostrarCurso(id, paginacion);
        return ResponseEntity.ok(response);
    }
}