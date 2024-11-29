package com.aluracursos.aluraforo.servicios;

import com.aluracursos.aluraforo.entidades.Perfil;
import com.aluracursos.aluraforo.repositorio.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PerfilService implements IPerfilService{
    @Autowired
    private PerfilRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Perfil> listarPerfil() {
        return (List<Perfil>) repository.findAll();
    }

    @Override
    @Transactional
    public Perfil guardar(Perfil perfil) {
        return(repository.save(perfil));
    }

    @Override
    @Transactional
    public void eliminar(Perfil perfil) {
        repository.delete(perfil);
    }

    @Override
    @Transactional(readOnly = true)
    public Perfil encontrarPerfil(Perfil perfil) {
        return repository.findById(perfil.getId()).orElse(null);
    }
}
