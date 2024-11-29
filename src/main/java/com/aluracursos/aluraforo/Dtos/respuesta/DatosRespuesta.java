package com.aluracursos.aluraforo.Dtos.respuesta;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRespuesta(
                             @NotBlank
                             String mensaje,
                             @NotNull
                             Integer solucion,
                             @NotNull
                             Integer id_usuario,
                             @NotNull
                             Integer id_topico) {
}
