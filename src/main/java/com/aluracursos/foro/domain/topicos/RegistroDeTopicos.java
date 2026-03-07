package com.aluracursos.foro.domain.topicos;

import com.aluracursos.foro.domain.cursos.CursoRepository;
import com.aluracursos.foro.domain.usuarios.UsuarioRepository;
import com.aluracursos.foro.domain.topicos.validaciones.ValidadorDeTopicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroDeTopicos {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private List<ValidadorDeTopicos> validadores;

    public DatosDetalleTopico registrar(DatosRegistroTopicos datos){

        validadores.forEach(v -> v.validar(datos));

        var autor = usuarioRepository.getReferenceById(datos.autorId());
        var curso = cursoRepository.getReferenceById(datos.cursoId());

        var topico = new Topico(datos, autor, curso);

        repository.save(topico);

        return new DatosDetalleTopico(topico);
    }
}
