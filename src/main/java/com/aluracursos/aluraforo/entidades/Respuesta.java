package com.aluracursos.aluraforo.entidades;

import com.aluracursos.aluraforo.Dtos.respuesta.DatosActualizaRespuesta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Respuesta")
@Table(name = "respuesta")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private int solucion;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_topico")
    private Topico topico;

    public void actualizarDatos(DatosActualizaRespuesta datosActualizarRespuesta) {
    this.mensaje = datosActualizarRespuesta.mensaje();
    this.solucion = datosActualizarRespuesta.solucion();
    }
}
