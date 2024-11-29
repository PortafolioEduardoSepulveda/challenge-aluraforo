package com.aluracursos.aluraforo.Dtos.autentificacion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(
        @NotBlank
        @Email
        String login,
        @NotBlank
        String clave) {
}
