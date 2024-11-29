package com.aluracursos.aluraforo.Dtos.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
                                     @NotNull
                                     Integer id,
                                     @NotBlank
                                     String nombre) {
}
