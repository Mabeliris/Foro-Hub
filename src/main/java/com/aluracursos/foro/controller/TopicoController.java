package com.aluracursos.foro.controller;


import com.aluracursos.foro.domain.topicos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private RegistroDeTopicos registroDeTopicos;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private ActualizarTopico actualizarTopico;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopicos datos, UriComponentsBuilder uriComponentsBuilder){
        var detalle = registroDeTopicos.registrar(datos);

        var uri= uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(detalle.id()).toUri();

        return ResponseEntity.created(uri).body(detalle);
    }

    @Autowired
    private PagedResourcesAssembler<TopicoDto> pagedResourcesAssembler;

    @Autowired
    private TopicoDtoModelAssembler topicoDtoModelAssembler;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<TopicoDto>>> listarTopico(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion){
        Page<TopicoDto> pagina =topicoRepository.findAllByActivoTrue(paginacion).map(TopicoDto::new);

        var page = pagedResourcesAssembler.toModel(pagina, topicoDtoModelAssembler);

        return ResponseEntity.ok(page);

    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id){
        var topico= topicoRepository.getReferenceById(id);
        topico.getId();
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }


    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid ActualizarTopicoDto datosActualizados){

        var respuesta = actualizarTopico.actualizar(datosActualizados);
        return ResponseEntity.ok(respuesta);

    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){

        var topico = topicoRepository.findById(id);

        if (topico.isPresent()&& topico.get().isActivo()){
            topico.get().eliminarTopico();
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }





}
