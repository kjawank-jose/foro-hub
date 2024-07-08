package com.kjawank.forohub.domain.topic.answer.validations;

import com.kjawank.forohub.domain.topic.TopicRepository;
import com.kjawank.forohub.domain.topic.answer.DataCreateResponse;
import com.kjawank.forohub.domain.topic.answer.ResponseRepository;
import com.kjawank.forohub.domain.user.UserRepository;
import com.kjawank.forohub.infra.errores.ValidationOfIntegrity;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RespuestaValida implements ValidadorRespuesta{

    @Autowired
    ResponseRepository responseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicoRepository;

    public void validar(DataCreateResponse datos){
        if (datos.mensaje() == null){
            throw new ValidationOfIntegrity("Elmemsaje no fue encontrado");
        }
        if (responseRepository.existsByTopicoAndMensajeAndAutorRespuesta(
                topicoRepository.getReferenceById(datos.idTopico()),
                datos.mensaje(),
                userRepository.getReferenceById(datos.idAutor()))){
            throw new ValidationException("Se esta duplicando la respuesta para el tópico");
        }
    }
}
