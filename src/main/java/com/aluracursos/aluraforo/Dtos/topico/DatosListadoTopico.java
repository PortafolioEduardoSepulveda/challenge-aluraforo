package com.aluracursos.aluraforo.Dtos.topico;

import com.aluracursos.aluraforo.entidades.Curso;
import com.aluracursos.aluraforo.entidades.DatosUsuario;
import com.aluracursos.aluraforo.entidades.Topico;

import java.time.LocalDateTime;


public record DatosListadoTopico(String titulo,
                                 String mensaje,
                                 LocalDateTime fechaCreacion,
                                 String status,
                                // DatosUsuario usuario,
                                 String usuario,
                                // Curso Curso) {
                                 String Curso) {
    public DatosListadoTopico(Topico topico) {
       // this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),new DatosUsuario(topico.getUsuario()) ,topico.getCurso());
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),topico.getUsuario().getNombre() ,topico.getCurso().getNombre());
    }
}
