package com.aluracursos.aluraforo.servicios;

import com.aluracursos.aluraforo.entidades.Curso;

import java.util.List;

public interface ICursoService {
    public List<Curso> listarCurso();

    public Curso guardar(Curso curso);

    public void eliminar(Curso curso);

    public Curso encontrarCurso(Curso curso);
}
