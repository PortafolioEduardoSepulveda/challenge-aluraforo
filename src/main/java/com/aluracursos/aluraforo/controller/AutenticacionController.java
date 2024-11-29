
package com.aluracursos.aluraforo.controller;

import com.aluracursos.aluraforo.Dtos.autentificacion.DatosAutenticacion;
import com.aluracursos.aluraforo.Dtos.autentificacion.DatosJWTToken;
import com.aluracursos.aluraforo.entidades.Usuario;
import com.aluracursos.aluraforo.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid DatosAutenticacion datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.login(),
                datos.clave());
        var usuarioAutenticado = manager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
