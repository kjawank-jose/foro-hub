package com.kjawank.forohub.domain.topic.answer.validations;

import com.kjawank.forohub.domain.topic.TopicRepository;
import com.kjawank.forohub.domain.topic.answer.DataCreateResponse;
import com.kjawank.forohub.infra.errores.ValidationOfIntegrity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoValido implements ValidadorRespuesta{

    @Autowired
    TopicRepository topicoRepository;

    public void validar(DataCreateResponse datos){
        if (datos.idTopico() == null || !topicoRepository.existsById(datos.idTopico())){
            throw new ValidationOfIntegrity("El Tópico no fue encontrado");
        }
    }
}
