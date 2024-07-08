package com.kjawank.forohub.domain.course;

import com.kjawank.forohub.domain.course.validations.ValidatorCourse;
import com.kjawank.forohub.domain.topic.DataTopic;
import com.kjawank.forohub.domain.topic.TopicRepository;
import com.kjawank.forohub.infra.errores.ValidationOfIntegrity;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository cursoRepository;
    @Autowired
    private TopicRepository topicoRepository;
    @Autowired
    private List<ValidatorCourse> validatorCourses;

    public DataCourse crearCurso(DataCreateCourse datos){
        validatorCourses.forEach(v -> v.validar(datos));
        var curso = new Course(datos.nombre(), datos.categoria());
        cursoRepository.save(curso);
        return new DataCourse(curso);
    }

    public DataCourse actualizarCurso(DataUpdateCourse datos){
        cursoExistente(datos.id());
        var curso = cursoRepository.getReferenceById(datos.id());
        curso.actualizar(datos);
        return new DataCourse(curso);
    }

    public DataCourse cambiarEstado(Long id){
        cursoExistente(id);
        var curso = cursoRepository.getReferenceById(id);
        curso.setActivo();
        return new DataCourse(curso);
    }

    private void cursoExistente(Long id){
        if(id == null){
            throw new ValidationException("Ingrese el Id del curso");
        }
        if (!cursoRepository.existsById(id)){
            throw new ValidationOfIntegrity("Ingrese un Id valido");
        }
    }

    public Page<DataCourse> listarCursoActivos(Pageable paginacion){
        return cursoRepository.findByActivoTrue(paginacion).map(DataCourse::new);
    }

    public Page<DataCourse> listarCursosInactivos(Pageable paginacion){
        return cursoRepository.findByActivoFalse(paginacion).map(DataCourse::new);
    }

    public Page<DataCourse> listarCursos(Pageable paginacion){
        return cursoRepository.findAll(paginacion).map(DataCourse::new);
    }

    public DataCourseTopics mostrarCurso(Long id, Pageable paginacion){
        cursoExistente(id);
        var curso = cursoRepository.getReferenceById(id);
        var topicos = topicoRepository.findAllByCurso(curso, paginacion).map(DataTopic::new);
        return new DataCourseTopics(new DataCourse(curso), topicos);
    }
}

















