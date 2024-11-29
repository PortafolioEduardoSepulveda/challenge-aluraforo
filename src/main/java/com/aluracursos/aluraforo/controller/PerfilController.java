package com.aluracursos.aluraforo.controller;

import com.aluracursos.aluraforo.Dtos.perfil.DatosActualizaPerfil;
import com.aluracursos.aluraforo.Dtos.perfil.DatosPerfil;
import com.aluracursos.aluraforo.Dtos.perfil.DatosRespuestaPerfil;
import com.aluracursos.aluraforo.Dtos.perfil.DatosRespuestaPerfilRegistrado;
import com.aluracursos.aluraforo.entidades.Perfil;
import com.aluracursos.aluraforo.entidades.Usuario;
import com.aluracursos.aluraforo.infra.errores.ValidacionException;
import com.aluracursos.aluraforo.servicios.IPerfilService;
import com.aluracursos.aluraforo.servicios.IUsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/perfil")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {
    @Autowired
    private IPerfilService repositorio;
    @Autowired
    private IUsuarioService repositorio_usuario;

    @GetMapping
    public ResponseEntity<List<Perfil>> listadoPerfil() {
//        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(repositorio.listarPerfil());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPerfil> retornaDatosPerfil(@PathVariable Integer id) {
        Perfil PerfilBuscado = new Perfil();
        PerfilBuscado.setId(id);
        Perfil perfil = repositorio.encontrarPerfil(PerfilBuscado);
        if(perfil == null){
            throw new ValidacionException("Perfil no Existe!");
        }
        var respuestaPerfil = new DatosRespuestaPerfil(perfil.getId(), perfil.getNombre());
        return ResponseEntity.ok(respuestaPerfil);
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaPerfil> registrarPerfil(@RequestBody @Valid DatosPerfil datosPerfil,
                                                                          UriComponentsBuilder uriComponentsBuilder) {

        Perfil perfil = new Perfil();
        perfil.setNombre(datosPerfil.nombre());


        Perfil perfilRegistrado = repositorio.guardar(perfil);
        DatosRespuestaPerfil datosRespuestaPerfil = new DatosRespuestaPerfil(perfilRegistrado.getId(), perfilRegistrado.getNombre());

        URI url = uriComponentsBuilder.path("/perfil/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPerfil);

    }


    @PutMapping
    public ResponseEntity<DatosRespuestaPerfil> actualizarUsuario(@RequestBody @Valid DatosActualizaPerfil datosActualizarPerfil) {
        Perfil perfil = new Perfil();
        perfil.setId(datosActualizarPerfil.id());
        Perfil perfilBuscado = repositorio.encontrarPerfil(perfil);
        if(perfilBuscado == null){
            throw new ValidacionException("Perfil no Existe!");
        }
        perfilBuscado.actualizarDatos(datosActualizarPerfil);
        repositorio.guardar(perfilBuscado);
        return ResponseEntity.ok(new DatosRespuestaPerfil(perfilBuscado.getId(),perfilBuscado.getNombre()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPerfil(@PathVariable Integer id) {
        Perfil perfil = new Perfil();
        perfil.setId(id);
        Perfil perfilBorrar = repositorio.encontrarPerfil(perfil);
        if(perfilBorrar == null){
            throw new ValidacionException("Perfil no Existe!");
        }
        repositorio.eliminar(perfil);
        return ResponseEntity.noContent().build();
    }
}
