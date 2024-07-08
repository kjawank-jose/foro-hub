package com.kjawank.forohub.controller;

import com.kjawank.forohub.domain.topic.StatusTopicResponse;
import com.kjawank.forohub.domain.topic.answer.DataCreateResponse;
import com.kjawank.forohub.domain.topic.answer.DataEditResponse;
import com.kjawank.forohub.domain.topic.answer.ResponseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class ResponseController {

    @Autowired
    private ResponseService service;

    @PostMapping
    @Transactional
    public ResponseEntity crearRespuesta(
            @RequestBody
            @Valid
            DataCreateResponse datos){
        var response = service.crearRespuesta(datos);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarRespuesta(
            @RequestBody
            @Valid
            DataEditResponse datos){
        var response = service.editarRespuesta(datos);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity cambiarEstadoRespuesta(
            @PathVariable
            Long id){
        StatusTopicResponse response = service.marcarDesmarcarComoSolucion(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(
            @PathVariable
            Long id){
        service.eliminarRespuesta(id);
        return ResponseEntity.ok().build();
    }
}











