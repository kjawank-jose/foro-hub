package com.kjawank.forohub.domain.course.validations;

import com.kjawank.forohub.domain.course.DataCreateCourse;
import com.kjawank.forohub.infra.errores.ValidationOfIntegrity;
import org.springframework.stereotype.Component;

@Component
public class CategoryValid implements ValidatorCourse {
    @Override
    public void validar(DataCreateCourse datos){
        if (datos.categoria() == null){
            throw new ValidationOfIntegrity("El curso debe estar asignado a una categoria.");
        }
    }
}
