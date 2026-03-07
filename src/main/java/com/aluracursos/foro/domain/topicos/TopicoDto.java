package com.aluracursos.foro.domain.topicos;

import com.aluracursos.foro.domain.cursos.Curso;
import com.aluracursos.foro.domain.usuarios.Usuario;

import java.time.LocalDateTime;

public record TopicoDto(
        Long id,
        String título,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        EstadoTopico estado,
        Usuario autor,
        Curso curso
) {

    public TopicoDto(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }

}