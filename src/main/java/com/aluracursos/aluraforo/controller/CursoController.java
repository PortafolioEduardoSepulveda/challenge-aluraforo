package com.aluracursos.aluraforo.controller;

import com.aluracursos.aluraforo.Dtos.curso.DatosActualizaCurso;
import com.aluracursos.aluraforo.Dtos.curso.DatosCurso;
import com.aluracursos.aluraforo.Dtos.curso.DatosRespuestaCurso;
import com.aluracursos.aluraforo.entidades.Curso;
import com.aluracursos.aluraforo.infra.errores.ValidacionException;
import com.aluracursos.aluraforo.servicios.ICursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/curso")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    @Autowired
    private ICursoService repositorio;

    @GetMapping
    public ResponseEntity<List<Curso>> listadoCurso() {
//        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(repositorio.listarCurso());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> retornaDatosCurso(@PathVariable Integer id) {
        Curso cursoBuscado = new Curso();
        cursoBuscado.setId(id);
        Curso curso = repositorio.encontrarCurso(cursoBuscado);
        if(curso == null){
            throw new ValidacionException("Curso no Existe!");
        }
        var respuestaPerfil = new DatosRespuestaCurso(curso.getId(), curso.getNombre(),curso.getCategoria());
        return ResponseEntity.ok(respuestaPerfil);
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosCurso datosCurso,
                                                                          UriComponentsBuilder uriComponentsBuilder) {
        Curso curso = new Curso();
        curso.setNombre(datosCurso.nombre());
        curso.setCategoria(datosCurso.categoria());

        Curso perfilCurso = repositorio.guardar(curso);
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(perfilCurso.getId(), perfilCurso.getNombre(),perfilCurso.getCategoria());

        URI url = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(perfilCurso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);

    }
    @PutMapping
    public ResponseEntity<DatosRespuestaCurso> actualizarCurso(@RequestBody @Valid DatosActualizaCurso datosActualizarCurso) {
        Curso curso = new Curso();
        curso.setId(datosActualizarCurso.id());
        Curso cursoBuscado = repositorio.encontrarCurso(curso);
        if(cursoBuscado == null){
            throw new ValidacionException("Curso ha Actualizar no Existe!");
        }
        cursoBuscado.actualizarDatos(datosActualizarCurso);
        repositorio.guardar(cursoBuscado);
        return ResponseEntity.ok(new DatosRespuestaCurso(cursoBuscado.getId(),cursoBuscado.getNombre(),cursoBuscado.getCategoria()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPerfil(@PathVariable Integer id) {
        Curso curso = new Curso();
        curso.setId(id);
        Curso cursoBorrar = repositorio.encontrarCurso(curso);
        if(cursoBorrar == null){
            throw new ValidacionException("Curso ha borra no Existe!");
        }
        repositorio.eliminar(cursoBorrar);
        return ResponseEntity.noContent().build();
    }
}
