package com.aluracursos.foro.domain.topicos;

import com.aluracursos.foro.domain.cursos.Curso;
import com.aluracursos.foro.domain.respuestas.Respuesta;
import com.aluracursos.foro.domain.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean activo;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private EstadoTopico status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas;

    public Topico( DatosRegistroTopicos datos, Usuario autor, Curso curso) {
        this.id= null;
        this.activo=true;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = EstadoTopico.SIN_RESPUESTA;
        this.autor = autor;
        this.curso = curso;
    }


    public void actualizarInformacion(@Valid ActualizarTopicoDto datosActualizados) {
        if(datosActualizados.titulo() != null){
            this.titulo = datosActualizados.titulo();
        }

        if(datosActualizados.mensaje() != null){
            this.mensaje = datosActualizados.mensaje();
        }
    }

    public void eliminarTopico(){
        this.activo=false;
    }
}
