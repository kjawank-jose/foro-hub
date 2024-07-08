package com.kjawank.forohub.domain.topic.validations;

import com.kjawank.forohub.domain.course.CourseRepository;
import com.kjawank.forohub.domain.topic.DataCreateTopic;
import com.kjawank.forohub.infra.errores.ValidationOfIntegrity;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CursoExistente implements ValidadorTopico {
    @Autowired
    private CourseRepository cursoRepository;

    @Override
    public void validar(DataCreateTopic datos){
        if (datos.idCurso() == null){
            throw new ValidationOfIntegrity("El tópico debe tener un curso asignado");
        }
        var curso = cursoRepository.findById(datos.idCurso());
        if (!curso.isPresent()){
            throw new ValidationException("El tópico no existe.");
        }
    }
}
