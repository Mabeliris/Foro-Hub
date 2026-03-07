package com.aluracursos.foro.domain.topicos;


import com.aluracursos.foro.domain.ValidacionExcepcion;
import com.aluracursos.foro.domain.topicos.validaciones.ValidadorDeTopicos;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActualizarTopico {

    @Autowired
    private TopicoRepository repository;

    public DatosDetalleTopico actualizar(ActualizarTopicoDto datos){

        var optionalTopico = repository.findById(datos.id());

        if(optionalTopico.isPresent()){

            var topico = optionalTopico.get();

            // verificar si no hay cambios
            if(topico.getTitulo().equals(datos.titulo().trim()) &&
                    topico.getMensaje().equals(datos.mensaje().trim())){

                throw new ValidacionExcepcion("No hay cambios para actualizar");
            }

            // actualizar
            topico.actualizarInformacion(datos);

            return new DatosDetalleTopico(topico);
        }

        throw new EntityNotFoundException();
    }
}