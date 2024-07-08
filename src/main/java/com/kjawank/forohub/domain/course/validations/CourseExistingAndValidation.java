package com.kjawank.forohub.domain.course.validations;

import com.kjawank.forohub.domain.course.CourseRepository;
import com.kjawank.forohub.domain.course.DataCreateCourse;
import com.kjawank.forohub.infra.errores.ValidationOfIntegrity;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseExistingAndValidation implements ValidatorCourse {

    @Autowired
    CourseRepository repository;

    @Override
    public void validar(DataCreateCourse datos){
        if (repository.existsByNombre(datos.nombre())){
            throw new ValidationException("El curso ya fue registrado");
        }
        if (datos.nombre() == null){
            throw new ValidationOfIntegrity("no deje el nombre del curso vacio");
        }
    }
}
