package com.aluracursos.aluraforo.Dtos.curso;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCurso(
        @NotBlank
        String nombre,
        @NotNull
        String categoria) {
}
