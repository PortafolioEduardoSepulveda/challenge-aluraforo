
package com.aluracursos.aluraforo.infra.security;


import com.aluracursos.aluraforo.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AutenticacionService implements UserDetailsService {

    private UsuarioRepository repository;

    @Autowired
    public AutenticacionService(UsuarioRepository usuarioRepository){
        this.repository =  usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return repository.findByEmail(username);

    }
}
