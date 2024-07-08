package com.kjawank.forohub.domain.topic.answer;

import com.kjawank.forohub.domain.topic.DataTopic;
import com.kjawank.forohub.domain.topic.StatusTopicResponse;
import com.kjawank.forohub.domain.topic.TopicRepository;
import com.kjawank.forohub.domain.topic.answer.validations.ValidadorRespuesta;
import com.kjawank.forohub.domain.user.UserRepository;
import com.kjawank.forohub.infra.errores.ValidationOfIntegrity;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicoRepository;
    @Autowired
    private List<ValidadorRespuesta> validarRespuesta;

    public DataResponse crearRespuesta(DataCreateResponse datos) {

        validarRespuesta.forEach(v -> v.validar(datos));
        var mensaje = datos.mensaje();
        var autor = userRepository.getReferenceById(datos.idAutor());
        var topico = topicoRepository.getReferenceById(datos.idTopico());
        var respuesta = new Response(mensaje, autor, topico);
        repository.save(respuesta);
        return new DataResponse(respuesta);
    }

    public DataResponse editarRespuesta(DataEditResponse datos) {
        if (datos.mensaje() == null) {
            throw new ValidationException("respuesta no encontrada");
        }
        if (!repository.existsById(datos.idAutor())) {
            throw new ValidationOfIntegrity("id de respuesta no válido");
        }
        var respuesta = repository.getReferenceById(datos.idAutor());
        respuesta.actualizarMensaje(datos.mensaje());
        return new DataResponse(respuesta);
    }

    public StatusTopicResponse marcarDesmarcarComoSolucion(Long id) {
        idValido(id);
        var respuesta = repository.getReferenceById(id);
        var topico = topicoRepository.getReferenceById(respuesta.getTopico().getId());
        respuesta.setSolucion();
        var solucionado = repository.existsByTopicoAndSolucion(topico, true);
        System.out.println(solucionado);
        if ((solucionado && topico.getStatus() == false) || (!solucionado && topico.getStatus() == true)) {
            topico.setStatus();
        }

        var resultado = new StatusTopicResponse(new DataTopic(topico), new DataResponse(respuesta));
        return resultado;
    }

    public void eliminarRespuesta(Long id) {
        idValido(id);
        var idTopicoRespuesta = repository.getReferenceById(id).getTopico().getId();
        var topico = topicoRepository.getReferenceById(idTopicoRespuesta);

        repository.deleteById(id);

        var topicoSolucionado = repository.existsByTopicoAndSolucion(topico, true);
        var estadoTopico = topico.getStatus();
        if (estadoTopico && !topicoSolucionado) {
            topico.setStatus();
        }
    }

    private void idValido(Long id) {
        if(id == null) {
            throw new ValidationException("Debe proporcionar el id de la respuesta");
        }

        if(!repository.existsById(id)) {
            throw new ValidationOfIntegrity("No existe respuesta con id: " + id);
        }
    }
}