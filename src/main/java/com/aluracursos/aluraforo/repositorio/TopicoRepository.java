package com.aluracursos.aluraforo.repositorio;


import com.aluracursos.aluraforo.entidades.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico,Integer> {
    @Query("""
            select t from Topico t
            Order by t.fechaCreacion asc
            limit 10
            """)
    List<Topico> Listas10TipicosAsc();
    @Query("""
            select t from Topico t
            join t.curso c where c.nombre = :nombre
            """)
    List<Topico> ListarTopicosCursoNombre(String nombre);
}
