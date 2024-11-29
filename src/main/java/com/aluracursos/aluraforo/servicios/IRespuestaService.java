package com.aluracursos.aluraforo.servicios;

import com.aluracursos.aluraforo.entidades.Respuesta;

import java.util.List;

public interface IRespuestaService {
    public List<Respuesta> listarRespuestas();

    public Respuesta guardar(Respuesta respuesta);

    public void eliminar(Respuesta respuesta);

    public Respuesta encontrarRespuesta(Respuesta respuesta);
}
