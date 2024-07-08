package com.kjawank.forohub.domain.topic.answer.validations;

import com.kjawank.forohub.domain.topic.answer.DataCreateResponse;
import com.kjawank.forohub.domain.user.UserRepository;
import com.kjawank.forohub.infra.errores.ValidationOfIntegrity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutorValido implements ValidadorRespuesta{

    @Autowired
    UserRepository userRepository;

    @Override
    public void validar(DataCreateResponse datos){
        if (datos.idAutor() == null || !userRepository.existsById(datos.idAutor())){
            throw new ValidationOfIntegrity("No se encontro el Autor ");
        }
    }
}
