package com.kjawank.forohub.domain.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Category categoria;
    private Boolean activo;

    public Course(String nombre, Category categoria){
        this.nombre = nombre;
        this.categoria = categoria;
        this.activo = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }

    public void actualizar(DataUpdateCourse datos){
        if (datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if (datos.categoria() != null){
            this.categoria = datos.categoria();
        }
    }
    public void setActivo() {
        this.activo = !activo;
    }
}
