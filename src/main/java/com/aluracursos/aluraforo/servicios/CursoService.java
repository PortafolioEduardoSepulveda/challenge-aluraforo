package com.aluracursos.aluraforo.servicios;

import com.aluracursos.aluraforo.entidades.Curso;
import com.aluracursos.aluraforo.repositorio.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoService  implements ICursoService {
    @Autowired
    private CursoRepository repository;


    @Override
    @Transactional(readOnly = true)
    public List<Curso> listarCurso() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return(repository.save(curso));
    }

    @Override
    @Transactional
    public void eliminar(Curso curso) {
        repository.delete(curso);
    }

    @Override
    @Transactional(readOnly = true)
    public Curso encontrarCurso(Curso curso) {
        return repository.findById(curso.getId()).orElse(null);
    }
}
