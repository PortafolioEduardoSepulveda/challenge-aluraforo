package com.aluracursos.aluraforo.servicios;

import com.aluracursos.aluraforo.entidades.Usuario;
import com.aluracursos.aluraforo.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
         return(repository.save(usuario));
    }

    @Override
    @Transactional
    public void eliminar(Usuario usuario) {
        repository.delete(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario encontrarUsuario(Usuario usuario) {
        return repository.findById(usuario.getId()).orElse(null);
    }
}
