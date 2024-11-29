package com.aluracursos.aluraforo.servicios;

import com.aluracursos.aluraforo.entidades.Perfil;


import java.util.List;

public interface IPerfilService {
    public List<Perfil> listarPerfil();

    public Perfil guardar(Perfil perfil);

    public void eliminar(Perfil perfil);

    public Perfil encontrarPerfil(Perfil Perfil);
}
