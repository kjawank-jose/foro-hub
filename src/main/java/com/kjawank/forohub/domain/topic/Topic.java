package com.kjawank.forohub.domain.topic;

import com.kjawank.forohub.domain.course.Course;
import com.kjawank.forohub.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_topico_id")
    private User autorTopico;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Course curso;

    public Topic(String titulo, String mensaje, User autor, Course curso){
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.status = false;
        this.autorTopico = autor;
        this.curso = curso;
    }

    public void actualizarTopico(DataUpdateTopic datos){
        if (datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
    }
    public void setStatus() {
        this.status = !status;
    }
}
