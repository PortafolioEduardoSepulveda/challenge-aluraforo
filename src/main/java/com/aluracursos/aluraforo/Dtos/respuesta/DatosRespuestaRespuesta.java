package com.aluracursos.aluraforo.Dtos.respuesta;

import com.aluracursos.aluraforo.entidades.Topico;
import com.aluracursos.aluraforo.entidades.Usuario;


public record DatosRespuestaRespuesta(
         Integer id,
         String mensaje,
         String fechaCreacion,
         int solucion,
         Usuario usuario,
         Topico topico
) {
}
