package com.aluracursos.aluraforo.repositorio;

import com.aluracursos.aluraforo.entidades.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso,Integer> {
}
