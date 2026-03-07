package com.aluracursos.foro.domain.topicos.validaciones;

import com.aluracursos.foro.domain.topicos.ActualizarTopicoDto;
import com.aluracursos.foro.domain.topicos.DatosRegistroTopicos;

public interface ValidadorDeTopicos {
    void validar(DatosRegistroTopicos datos);

}
