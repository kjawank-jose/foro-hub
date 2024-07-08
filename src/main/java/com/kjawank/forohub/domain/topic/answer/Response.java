package com.kjawank.forohub.domain.topic.answer;

import com.kjawank.forohub.domain.topic.Topic;
import com.kjawank.forohub.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topico_id")
    private Topic topico;
    private LocalDateTime fechaCreacion;

    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_respuesta_id")
    private User autorRespuesta;
    private Boolean solucion;

    public Response(String mensaje, User autor, Topic topico){
        this.mensaje = mensaje;
        this.topico = topico;
        this.autorRespuesta = autor;
        this.fechaCreacion = LocalDateTime.now();
        this.solucion = false;
    }

    public void setSolucion() {
        this.solucion = !solucion;
    }

    public void actualizarMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
