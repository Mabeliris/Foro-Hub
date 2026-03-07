package com.aluracursos.foro.domain.topicos.validaciones;

import com.aluracursos.foro.domain.ValidacionExcepcion;
import com.aluracursos.foro.domain.topicos.ActualizarTopicoDto;
import com.aluracursos.foro.domain.topicos.DatosRegistroTopicos;
import com.aluracursos.foro.domain.topicos.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class ValidacionTopicoDuplicado implements ValidadorDeTopicos{


    @Autowired
    private TopicoRepository repository;

    @Override
    public void validar(DatosRegistroTopicos datos) {

        if(repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())){
            throw new ValidacionExcepcion("Ya existe un tópico con el mismo título y mensaje");
        }

    }


}
