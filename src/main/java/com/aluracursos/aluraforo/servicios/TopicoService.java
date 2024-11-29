package com.aluracursos.aluraforo.servicios;


import com.aluracursos.aluraforo.entidades.Topico;
import com.aluracursos.aluraforo.repositorio.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicoService implements ITopicoService {
    @Autowired
    private TopicoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Topico> listarTopico() {
        return (List<Topico>) repository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Page<Topico>  listarTopicoPaginado(Pageable paginacion) {
        return (Page<Topico>) repository.findAll(paginacion);
    }

    @Override
    public List<Topico> Listat10Tipicos() {
        return repository.Listas10TipicosAsc();
    }

    @Override
    public boolean existeTopicoPorTituloAndMensage(String titulo, String mensaje) {
        return repository.existsByTituloAndMensaje(titulo,mensaje);
    }

    @Override
    @Transactional
    public Topico guardar(Topico topico) {
        return(repository.save(topico));
    }

    @Override
    public List<Topico> ListadoTopicosCursoNombre(String nombre) {
        return repository.ListarTopicosCursoNombre(nombre);
    }

    @Override
    @Transactional
    public void eliminar(Topico topico) {
        repository.delete(topico);
    }

    @Override
    @Transactional(readOnly = true)
    public Topico encontrarTopico(Topico topico) {
        return repository.findById(topico.getId()).orElse(null);
    }
}
