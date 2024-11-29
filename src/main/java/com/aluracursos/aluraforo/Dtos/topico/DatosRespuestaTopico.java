package com.aluracursos.aluraforo.Dtos.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(Integer id,
                                   String titulo,
                                   String mensaje,
                                   LocalDateTime fechaCreacion,
                                   String status) {

}
