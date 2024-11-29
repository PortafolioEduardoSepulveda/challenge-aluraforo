package com.aluracursos.aluraforo.repositorio;

import com.aluracursos.aluraforo.entidades.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil,Integer> {
    Optional<Perfil> findByNombre(String name);
}
