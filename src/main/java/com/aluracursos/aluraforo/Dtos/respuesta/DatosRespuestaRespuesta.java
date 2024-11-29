package com.aluracursos.aluraforo.Dtos.respuesta;

import com.aluracursos.aluraforo.entidades.DatosUsuario;
import com.aluracursos.aluraforo.entidades.Respuesta;
import com.aluracursos.aluraforo.entidades.Topico;
import com.aluracursos.aluraforo.entidades.Usuario;

import java.time.LocalDateTime;


public record DatosRespuestaRespuesta(
         Integer id,
         String mensaje,
         LocalDateTime fechaCreacion,
         int solucion,
         DatosUsuario usuario,
         Topico topico
) {
    public DatosRespuestaRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaCreacion(), respuesta.getSolucion(),new DatosUsuario(respuesta.getUsuario()) ,respuesta.getTopico());
    }
}
