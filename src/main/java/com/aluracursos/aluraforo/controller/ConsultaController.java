package com.aluracursos.aluraforo.controller;

import com.aluracursos.aluraforo.Dtos.topico.DatosListadoTopico;
import com.aluracursos.aluraforo.servicios.ITopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.stream.Stream;


@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    private ITopicoService repositorio_topico;

    @GetMapping("/topicopaginado")
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopico(@PageableDefault(size = 10) Pageable paginacion) {
//        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(repositorio_topico.listarTopicoPaginado(paginacion).map(DatosListadoTopico::new));
    }
    @GetMapping("/topico10ordenado")
    public ResponseEntity<Stream<DatosListadoTopico>> listadoTopico10() {
//        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(repositorio_topico.Listat10Tipicos().stream().map(DatosListadoTopico::new));
    }
    @GetMapping("/topicocursonombre/{nombre}")
    public ResponseEntity<Stream<DatosListadoTopico>> listadoTopico10(@PathVariable String nombre) {
//        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(repositorio_topico.ListadoTopicosCursoNombre(nombre).stream().map(DatosListadoTopico::new));
    }
}
