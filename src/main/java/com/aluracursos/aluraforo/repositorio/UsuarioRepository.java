package com.aluracursos.aluraforo.repositorio;


import com.aluracursos.aluraforo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    UserDetails findByEmail(String username);
    Boolean existsByEmail(String username);
}
