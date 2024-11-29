package com.aluracursos.aluraforo.Dtos.perfil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizaPerfil(
        @NotNull
        Integer id,
        @NotBlank
        String nombre) {
}
