package com.aluracursos.foro.domain.topicos;

import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
public class TopicoDtoModelAssembler implements RepresentationModelAssembler<TopicoDto, EntityModel<TopicoDto>> {
    @Override
    public @NonNull EntityModel<TopicoDto> toModel(TopicoDto datoslistaTopico) {
        return EntityModel.of(datoslistaTopico);

    }
}
