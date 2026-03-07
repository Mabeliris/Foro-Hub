-- =========================
-- Tabla cursos
-- =========================
CREATE TABLE cursos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

-- =========================
-- Tabla usuarios
-- =========================
CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

-- =========================
-- Tabla perfiles
-- =========================
CREATE TABLE perfiles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

-- =========================
-- Tabla usuarios_perfiles (ManyToMany)
-- =========================
CREATE TABLE usuarios_perfiles (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    CONSTRAINT fk_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_perfil
        FOREIGN KEY (perfil_id)
        REFERENCES perfiles(id)
        ON DELETE CASCADE
);

-- =========================
-- Tabla topicos
-- =========================
CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    autor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_topico_autor
        FOREIGN KEY (autor_id)
        REFERENCES usuarios(id),
    CONSTRAINT fk_topico_curso
        FOREIGN KEY (curso_id)
        REFERENCES cursos(id)
);

-- =========================
-- Tabla respuestas
-- =========================
CREATE TABLE respuestas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    solucion BOOLEAN NOT NULL,
    topico_id BIGINT NOT NULL,
    autor_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_respuesta_topico
        FOREIGN KEY (topico_id)
        REFERENCES topicos(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_respuesta_autor
        FOREIGN KEY (autor_id)
        REFERENCES usuarios(id)
);