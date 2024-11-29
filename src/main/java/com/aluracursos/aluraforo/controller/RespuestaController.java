package com.aluracursos.aluraforo.controller;

import com.aluracursos.aluraforo.Dtos.respuesta.DatosActualizaRespuesta;
import com.aluracursos.aluraforo.Dtos.respuesta.DatosListadoRespuesta;
import com.aluracursos.aluraforo.Dtos.respuesta.DatosRespuesta;
import com.aluracursos.aluraforo.Dtos.respuesta.DatosRespuestaRespuesta;
import com.aluracursos.aluraforo.Dtos.topico.DatosActualizaTopico;
import com.aluracursos.aluraforo.Dtos.topico.DatosListadoTopico;
import com.aluracursos.aluraforo.Dtos.topico.DatosRespuestaTopico;
import com.aluracursos.aluraforo.Dtos.usuario.DatosRespuestaUsuario;
import com.aluracursos.aluraforo.entidades.*;
import com.aluracursos.aluraforo.infra.errores.ValidacionException;
import com.aluracursos.aluraforo.servicios.IRespuestaService;
import com.aluracursos.aluraforo.servicios.ITopicoService;
import com.aluracursos.aluraforo.servicios.IUsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/respuesta")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {
    @Autowired
    private IRespuestaService repositorio;
    @Autowired
    private IUsuarioService repositorio_usuario;
    @Autowired
    private ITopicoService repositorio_topico;

    @GetMapping
    public ResponseEntity<Stream<DatosListadoRespuesta>> listadoRespuesta() {
//        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(repositorio.listarRespuestas().stream().map(DatosListadoRespuesta::new));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoRespuesta> retornaDatosRespuesta(@PathVariable Integer id) {
        Respuesta respuestaBuscada = new Respuesta();
        respuestaBuscada.setId(id);
        Respuesta respuesta = repositorio.encontrarRespuesta(respuestaBuscada);
        var respuestaRespuesta = new DatosListadoRespuesta(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaCreacion(), respuesta.getSolucion(), new DatosUsuario(respuesta.getUsuario()),respuesta.getTopico());
        return ResponseEntity.ok(respuestaRespuesta);
    }
    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> registrarRespuesta(@RequestBody @Valid DatosRespuesta datosRespuesta,
                                                                  UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = new Usuario();
        usuario.setId(datosRespuesta.id_usuario());
        Usuario usuarioBuscado = repositorio_usuario.encontrarUsuario(usuario);
        if(usuarioBuscado == null){
            throw new ValidacionException("Usuario no Existe!");
        }
        Topico topico = new Topico();
        topico.setId(datosRespuesta.id_topico());
        Topico topicoBuscado = repositorio_topico.encontrarTopico(topico);
        if(topicoBuscado == null){
            throw new ValidacionException("Topico no Existe!");
        }
        Respuesta  respuesta = new Respuesta();
        respuesta.setMensaje(datosRespuesta.mensaje());
        respuesta.setSolucion(datosRespuesta.solucion());
        respuesta.setUsuario(usuarioBuscado);
        respuesta.setTopico(topicoBuscado);

        Respuesta respuestaRegistrada = repositorio.guardar(respuesta);
        DatosRespuestaRespuesta datosRespuestaRespuesta = new DatosRespuestaRespuesta(respuestaRegistrada.getId(), respuestaRegistrada.getMensaje(),respuestaRegistrada.getFechaCreacion(), respuestaRegistrada.getSolucion(),respuestaRegistrada.getUsuario(),respuestaRegistrada.getTopico());

        URI url = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(respuestaRegistrada.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaRespuesta);

    }

    @PutMapping
    public ResponseEntity<DatosRespuestaRespuesta> actualizarTopico(@RequestBody @Valid DatosActualizaRespuesta datosActualizarRespuesta) {
        Respuesta respuesta = new Respuesta();
        respuesta.setId(datosActualizarRespuesta.id());
        Respuesta respuestaBuscada = repositorio.encontrarRespuesta(respuesta);
        if(respuestaBuscada == null){
            throw new ValidacionException("Respuesta ha actualizar no Existe!");
        }
        respuestaBuscada.actualizarDatos(datosActualizarRespuesta);
        repositorio.guardar(respuestaBuscada);
        return ResponseEntity.ok(new DatosRespuestaRespuesta(respuestaBuscada.getId(),respuestaBuscada.getMensaje(),respuestaBuscada.getFechaCreacion(),respuestaBuscada.getSolucion(),respuestaBuscada.getUsuario(),respuestaBuscada.getTopico()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Integer id) {
        Respuesta respuesta = new Respuesta();
        respuesta.setId(id);
        Respuesta RespuestaBorrar = repositorio.encontrarRespuesta(respuesta);
        if(RespuestaBorrar == null){
            throw new ValidacionException("Respuesta ha borrar no Existe!");
        }
        repositorio.eliminar(RespuestaBorrar);
        return ResponseEntity.noContent().build();
    }
}
