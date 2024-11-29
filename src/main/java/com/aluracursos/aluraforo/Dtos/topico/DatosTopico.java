package com.aluracursos.aluraforo.Dtos.topico;

import jakarta.validation.constraints.*;

public record DatosTopico(
                          @NotBlank(message = "titulo es obligatorio")
                          String titulo,
                          @NotBlank(message = "mensaje es obligatorio")
                          String mensaje,
                          @NotNull(message = "id_curso is mandatory")
                          @Min(1)
                          @Max(999999)
                          Integer id_curso,
                          @NotNull(message = "id_usuario is mandatory")
                          @Min(1)
                          @Max(999999)
                          Integer id_usuario) {
}
