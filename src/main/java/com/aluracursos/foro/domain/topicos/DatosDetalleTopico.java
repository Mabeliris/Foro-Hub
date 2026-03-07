package com.aluracursos.foro.domain.topicos;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        EstadoTopico estado,
        String autor,
        String curso
) {
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
