package com.aluracursos.aluraforo.Dtos.respuesta;

import com.aluracursos.aluraforo.entidades.DatosUsuario;
import com.aluracursos.aluraforo.entidades.Respuesta;
import com.aluracursos.aluraforo.entidades.Topico;


public record DatosListadoRespuesta(
        Integer id,
        String mensaje,
        String fechaCreacion,
        int solucion,
        DatosUsuario usuario,
        Topico topico) {
    public DatosListadoRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaCreacion(), respuesta.getSolucion(),new DatosUsuario(respuesta.getUsuario()),respuesta.getTopico());
    }
}
