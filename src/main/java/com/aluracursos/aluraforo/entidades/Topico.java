package com.aluracursos.aluraforo.entidades;

import com.aluracursos.aluraforo.Dtos.topico.DatosActualizaTopico;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Topico")
@Table(name = "topico")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String status;
    @ManyToOne
    @JoinColumn(name="id_curso")
    @JsonBackReference
    private Curso curso;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    @JsonBackReference
    private Usuario usuario;

    public void actualizarDatos(DatosActualizaTopico datosActualizarTopico) {
        this.mensaje = datosActualizarTopico.mensaje();
        this.status = datosActualizarTopico.status();
    }
}
