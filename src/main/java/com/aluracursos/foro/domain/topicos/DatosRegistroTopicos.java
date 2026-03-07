package com.aluracursos.foro.domain.topicos;

import com.aluracursos.foro.domain.cursos.Curso;
import com.aluracursos.foro.domain.usuarios.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopicos(
        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotNull
        Long autorId,

        @NotNull
        Long cursoId
) {
}
