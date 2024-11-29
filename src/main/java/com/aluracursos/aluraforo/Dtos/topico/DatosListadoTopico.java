package com.aluracursos.aluraforo.Dtos.topico;

import com.aluracursos.aluraforo.entidades.Curso;
import com.aluracursos.aluraforo.entidades.DatosUsuario;
import com.aluracursos.aluraforo.entidades.Topico;


public record DatosListadoTopico(String titulo,
                                 String mensaje,
                                 String fechaCreacion,
                                 String status,
                                 DatosUsuario usuario,
                                 Curso Curso) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),new DatosUsuario(topico.getUsuario()) ,topico.getCurso());
    }
}
