package com.kjawank.forohub.domain.topic;

import com.kjawank.forohub.domain.course.CourseRepository;
import com.kjawank.forohub.domain.topic.answer.DataResponse;
import com.kjawank.forohub.domain.topic.answer.ResponseRepository;
import com.kjawank.forohub.domain.topic.validations.ValidadorTopico;
import com.kjawank.forohub.domain.user.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicoRepository;
    @Autowired
    private CourseRepository cursoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private List<ValidadorTopico> validarTopico;

    public DataTopic crearTopico(DataCreateTopic datos) {

        validarTopico.forEach(v -> v.validar(datos));

        var titulo = datos.titulo();
        var mensaje = datos.mensaje();
        var autor = userRepository.getReferenceById(datos.idAutor());
        var curso = cursoRepository.getReferenceById(datos.idCurso());

        var topico = new Topic(titulo, mensaje, autor, curso);

        topicoRepository.save(topico);

        return new DataTopic(topico);
    }

    public DataTopic actualizarTopico(DataUpdateTopic datos) {

        var topico = topicoRepository.getReferenceById(datos.id());
        topico.actualizarTopico(datos);

        return new DataTopic(topico);
    }

    public String eliminarTopico(Long id) {
        var topico = topicoRepository.findById(id);
        if (!topico.isPresent() || id == null) {
            throw new ValidationException("No existe topico con id = " + id);
        }
        responseRepository.removeAllByTopico(topicoRepository.getReferenceById(id));

        topicoRepository.removeById(id);

        return "Topico y respuestas eliminados correctamente.";
    }

    public Page<DataTopic> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DataTopic::new);
    }

    public DataTopicResponses mostrarTopico(Long id, Pageable paginacion) {
        if(id == null || !topicoRepository.existsById(id)) {
            throw new ValidationException("no existe topico con ese id");
        }
        var topico = topicoRepository.getReferenceById(id);
        var respuestas = responseRepository
                .findAllByTopico(topico, paginacion)
                .map(DataResponse::new);

        return new DataTopicResponses(new DataTopic(topico), respuestas);
    }

    public Page<DataTopic> listarTopicosResueltos(Pageable paginacion) {
        var topicosSolucionados = topicoRepository.findAllByStatusTrue(paginacion);
        return topicosSolucionados.map(DataTopic::new);
    }

    public Page<DataTopic> listarTopicosNoResueltos(Pageable paginacion) {
        var topicosSolucionados = topicoRepository.findAllByStatusFalse(paginacion);
        return topicosSolucionados.map(DataTopic::new);
    }
}