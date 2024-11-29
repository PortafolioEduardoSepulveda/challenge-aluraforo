package com.aluracursos.aluraforo.servicios;

import com.aluracursos.aluraforo.entidades.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> listarUsuarios();

    public Usuario guardar(Usuario usuario);

    public void eliminar(Usuario usuario);

    public Usuario encontrarUsuario(Usuario usuario);
}
