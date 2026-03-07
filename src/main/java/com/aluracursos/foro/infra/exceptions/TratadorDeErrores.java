package com.aluracursos.foro.infra.exceptions;

import com.aluracursos.foro.domain.ValidacionExcepcion;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(ValidacionExcepcion.class)
    public ResponseEntity gestionarErrorDeValidacion(ValidacionExcepcion e){


        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarCodigo404(){
        return ResponseEntity.notFound().build();

    }

    public record DatosErrorValidacion(String campo, String mensaje){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity manejarErrorDeTipo(){
        return ResponseEntity.badRequest().body("El ID debe ser un número válido");
    }


}
