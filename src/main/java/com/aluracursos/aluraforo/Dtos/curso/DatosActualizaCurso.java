package com.aluracursos.aluraforo.Dtos.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizaCurso(
        @NotNull
        Integer id,
        @NotBlank
        String nombre,
        @NotBlank
        String categoria) {
}
