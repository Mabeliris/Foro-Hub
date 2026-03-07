package com.aluracursos.foro.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizarTopicoDto(
        @NotNull
        Long id,

        @NotBlank
        String titulo,
        @NotBlank
        String mensaje) {
}
