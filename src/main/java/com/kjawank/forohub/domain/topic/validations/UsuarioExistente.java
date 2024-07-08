package com.kjawank.forohub.domain.topic.validations;

import com.kjawank.forohub.domain.topic.DataCreateTopic;
import com.kjawank.forohub.domain.user.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioExistente implements ValidadorTopico{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validar(DataCreateTopic datos){
        var usuario = userRepository.existsById(datos.idAutor());
        if (!usuario){
            new ValidationException("El Autor no esta registrado");
        }
    }
}
