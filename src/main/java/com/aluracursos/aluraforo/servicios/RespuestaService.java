package com.aluracursos.aluraforo.servicios;

import com.aluracursos.aluraforo.entidades.Respuesta;
import com.aluracursos.aluraforo.repositorio.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RespuestaService implements IRespuestaService{
    @Autowired
    private RespuestaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Respuesta> listarRespuestas() {
        return (List<Respuesta>) repository.findAll();
    }

    @Override
    @Transactional
    public Respuesta guardar(Respuesta respuesta) {
        return(repository.save(respuesta));
    }

    @Override
    @Transactional
    public void eliminar(Respuesta respuesta) {
        repository.delete(respuesta);
    }

    @Override
    @Transactional(readOnly = true)
    public Respuesta encontrarRespuesta(Respuesta respuesta) {
        return repository.findById(respuesta.getId()).orElse(null);
    }
}
