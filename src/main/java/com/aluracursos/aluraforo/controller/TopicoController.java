package com.aluracursos.aluraforo.controller;


import com.aluracursos.aluraforo.Dtos.topico.*;
import com.aluracursos.aluraforo.entidades.Curso;
import com.aluracursos.aluraforo.entidades.DatosUsuario;
import com.aluracursos.aluraforo.entidades.Topico;
import com.aluracursos.aluraforo.entidades.Usuario;
import com.aluracursos.aluraforo.infra.errores.ValidacionException;
import com.aluracursos.aluraforo.servicios.ICursoService;
import com.aluracursos.aluraforo.servicios.ITopicoService;
import com.aluracursos.aluraforo.servicios.IUsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@RestController
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private ITopicoService repositorio;

    @Autowired
    private IUsuarioService repositorio_usuario;

    @Autowired
    private ICursoService  repositorio_curso;

    @GetMapping
    public ResponseEntity<Stream<DatosListadoTopico>> listadoTopico() {
//        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(repositorio.listarTopico().stream().map(DatosListadoTopico::new));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> retornaDatosTopico(@PathVariable Integer id) {

        Topico topicoBuscado = new Topico();
        topicoBuscado.setId(id);
        Topico topico = repositorio.encontrarTopico(topicoBuscado);
        if(topico == null){
            throw new ValidacionException("Topico no Existe!");
        }
        //var respuestaTopico = new DatosListadoTopico(topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(),topico.getStatus(),new DatosUsuario(topico.getUsuario()),topico.getCurso());
        var respuestaTopico = new DatosListadoTopico(topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(),topico.getStatus(),topico.getUsuario().getNombre(),topico.getCurso().getNombre());
        return ResponseEntity.ok(respuestaTopico);
    }
    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosTopico datosTopico,
                                                                          UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = new Usuario();
        usuario.setId(datosTopico.id_usuario());
        Usuario usuarioBuscado = repositorio_usuario.encontrarUsuario(usuario);
        if(usuarioBuscado == null){
            throw new ValidacionException("Usuario no Existes!");
        }
        Curso curso = new Curso();
        curso.setId(datosTopico.id_curso());
        Curso cursoBuscado = repositorio_curso.encontrarCurso(curso);
        if(cursoBuscado == null){
            throw new ValidacionException("Curso no Existes!");
        }
        Topico topico = new Topico();
        topico.setTitulo(datosTopico.titulo());
        topico.setMensaje(datosTopico.mensaje());
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setStatus("Pendiente");
        topico.setCurso(cursoBuscado);
        topico.setUsuario(usuarioBuscado);
        boolean exiteTopico = repositorio.existeTopicoPorTituloAndMensage(topico.getTitulo(),topico.getMensaje());
        if(exiteTopico){
            throw new ValidacionException("El Topico a Ingresar Existe!");
        }
        Topico topicoRegistrado = repositorio.guardar(topico);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topicoRegistrado.getId(), topicoRegistrado.getTitulo(),topicoRegistrado.getMensaje(),topicoRegistrado.getFechaCreacion(),topicoRegistrado.getStatus());

        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topicoRegistrado.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);

    }
    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Integer id,@RequestBody @Valid DatosActualizaTopico datosActualizarTopico) {
        Topico topico = new Topico();
        topico.setId(id);
        Topico topicoBuscado = repositorio.encontrarTopico(topico);
        if(topicoBuscado == null){
            throw new ValidacionException("Topico ha actualizar no Existe!");
        }
        topicoBuscado.actualizarDatos(datosActualizarTopico);
        repositorio.guardar(topicoBuscado);
        return ResponseEntity.ok(new DatosRespuestaTopico(topicoBuscado.getId(),topicoBuscado.getTitulo(),topicoBuscado.getMensaje(),topicoBuscado.getFechaCreacion(),topicoBuscado.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Integer id) {
        Topico topico = new Topico();
        topico.setId(id);
        Topico topicoBorrar = repositorio.encontrarTopico(topico);
        if(topicoBorrar == null){
            throw new ValidacionException("Topico ha borrar no Existe!");
        }
        repositorio.eliminar(topicoBorrar);
        return ResponseEntity.noContent().build();
    }
}
