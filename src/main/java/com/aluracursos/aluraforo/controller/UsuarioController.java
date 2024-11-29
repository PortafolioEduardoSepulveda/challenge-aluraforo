package com.aluracursos.aluraforo.controller;

import com.aluracursos.aluraforo.Dtos.topico.DatosListadoTopico;
import com.aluracursos.aluraforo.Dtos.usuario.DatosActualizarUsuario;
import com.aluracursos.aluraforo.Dtos.usuario.DatosRespuestaUsuario;
import com.aluracursos.aluraforo.Dtos.usuario.DatosUsuario;
import com.aluracursos.aluraforo.Dtos.usuario.DatosUsuarioAddPerfil;
import com.aluracursos.aluraforo.entidades.Perfil;
import com.aluracursos.aluraforo.entidades.Usuario;
import com.aluracursos.aluraforo.infra.errores.ValidacionException;
import com.aluracursos.aluraforo.repositorio.PerfilRepository;
import com.aluracursos.aluraforo.servicios.IPerfilService;
import com.aluracursos.aluraforo.servicios.IUsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private IUsuarioService repositorio;

    @Autowired
    private IPerfilService repositorio_perfil;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @GetMapping
    public ResponseEntity<Stream<com.aluracursos.aluraforo.entidades.DatosUsuario>> listadoUsuario() {
//        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        //return ResponseEntity.ok(repositorio.listarUsuarios());
        return ResponseEntity.ok(repositorio.listarUsuarios().stream().map(usuario -> new com.aluracursos.aluraforo.entidades.DatosUsuario(usuario)));
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosUsuario datosUsuario,
         UriComponentsBuilder uriComponentsBuilder) {

        List<Perfil> perfiles = new ArrayList();

        Perfil perfil = new Perfil();
        perfil.setId(2);
        perfil.setNombre("ROLE_USER");
        perfiles.add(perfil);
        Usuario usuario = new Usuario();
        usuario.setNombre(datosUsuario.nombre());
        usuario.setEmail(datosUsuario.email());
        usuario.setContrasena(encoder.encode(datosUsuario.contrasena()));
        usuario.setNombre(datosUsuario.nombre());
        usuario.setPerfiles(perfiles);
        Usuario usuarioRegistrado = repositorio.guardar(usuario);
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuarioRegistrado.getId(), usuarioRegistrado.getNombre(), usuarioRegistrado.getEmail());

        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);

    }

    @PostMapping("/addPerfil")
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuarioaddperfil(@RequestBody @Valid DatosUsuarioAddPerfil datosUsuario,
                                                                  UriComponentsBuilder uriComponentsBuilder) {

        Boolean existePerfil = false;
        Usuario usuarioBuscar = new Usuario();
        usuarioBuscar.setId(datosUsuario.id_usuario());
        Usuario usuario = repositorio.encontrarUsuario(usuarioBuscar);
        if(usuario == null){
            throw new ValidacionException("Usuario no Encontrado!");
        }
        Perfil perfil = new Perfil();
        perfil.setId(datosUsuario.id_perfil());
        Perfil perfilEncontrado = repositorio_perfil.encontrarPerfil(perfil);
        if(perfilEncontrado == null){
            throw new ValidacionException("Perfil a ingresar no Encontrado!");
        }

        for (Perfil p : usuario.getPerfiles()) {
            if(p.getNombre().equals(perfilEncontrado.getNombre())) existePerfil = true;
        }
        if(existePerfil){
            throw new ValidacionException("Error Perfil ya asignado!");
        }
        usuario.addPerfil(perfilEncontrado);

        Usuario usuarioRegistrado = repositorio.guardar(usuario);
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuarioRegistrado.getId(), usuarioRegistrado.getNombre(), usuarioRegistrado.getEmail());

        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);

    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> retornaDatosUsuario(@PathVariable Integer id) {
        Usuario usuarioBuscar = new Usuario();
        usuarioBuscar.setId(id);
        Usuario usuario = repositorio.encontrarUsuario(usuarioBuscar);
        if(usuario == null){
            throw new ValidacionException("Usuario no Encontrado!");
        }
        var respuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail());
        return ResponseEntity.ok(respuestaUsuario);
    }

    @PutMapping
    public ResponseEntity actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = new Usuario();
        usuario.setId(datosActualizarUsuario.id());
        Usuario usuarioBuscado = repositorio.encontrarUsuario(usuario);
        if(usuarioBuscado == null){
            throw new ValidacionException("Usuario no Existe!");
        }
        usuarioBuscado.actualizarDatos(datosActualizarUsuario);
        repositorio.guardar(usuarioBuscado);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuarioBuscado.getId(),usuarioBuscado.getNombre(), usuarioBuscado.getEmail() ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Integer id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        Usuario usuarioBorrar = repositorio.encontrarUsuario(usuario);
        if(usuarioBorrar == null){
            throw new ValidacionException("Usuario ha borra no Existe!");
        }
        repositorio.eliminar(usuarioBorrar);
        return ResponseEntity.noContent().build();
    }
}
