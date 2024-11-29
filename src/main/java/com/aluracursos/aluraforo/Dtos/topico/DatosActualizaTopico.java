package com.aluracursos.aluraforo.Dtos.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizaTopico(
        @NotBlank
        String mensaje,
        @NotBlank
        String status
) {
}
