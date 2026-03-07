package com.aluracursos.foro.domain;

public class ValidacionExcepcion extends RuntimeException {
    public ValidacionExcepcion(String message) {
        super(message);
    }
}
