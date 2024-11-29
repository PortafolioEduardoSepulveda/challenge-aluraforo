package com.aluracursos.aluraforo.entidades;

import com.aluracursos.aluraforo.Dtos.curso.DatosActualizaCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Curso")
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String categoria;

    public void actualizarDatos(DatosActualizaCurso datosActualizarCurso) {
        this.nombre = datosActualizarCurso.nombre();
        this.categoria = datosActualizarCurso.categoria();
    }
}
