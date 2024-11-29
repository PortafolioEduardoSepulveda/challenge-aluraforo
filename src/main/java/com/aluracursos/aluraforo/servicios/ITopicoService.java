package com.aluracursos.aluraforo.servicios;

import com.aluracursos.aluraforo.entidades.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITopicoService {
    public List<Topico> listarTopico();
    public Page<Topico> listarTopicoPaginado(Pageable paginacion);
    public List<Topico> Listat10Tipicos();
    public boolean existeTopicoPorTituloAndMensage(String titulo,String mensaje);
    public Topico guardar(Topico topico);
    public List<Topico> ListadoTopicosCursoNombre(String nombre);
    public void eliminar(Topico topico);

    public Topico encontrarTopico(Topico topico);
}
