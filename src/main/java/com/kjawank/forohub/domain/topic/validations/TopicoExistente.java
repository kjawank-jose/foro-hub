package com.kjawank.forohub.domain.topic.validations;

import com.kjawank.forohub.domain.topic.DataCreateTopic;
import com.kjawank.forohub.domain.topic.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoExistente implements ValidadorTopico{
    @Autowired
    TopicRepository topicoRepository;

    @Override
    public void validar(DataCreateTopic datos){
        var tituloExistente = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());

        if (tituloExistente != null && tituloExistente) {
            throw new ValidationException("El tópico ya existe");
        }
    }
}
