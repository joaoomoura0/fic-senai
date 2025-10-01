package com.ficsenai.FIC.model.pessoas;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(length = 100, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerfilUsuario perfil;

    @Column(name = "id_referencia")
    private Integer idReferencia; // Pode referenciar id_aluno, id_docente, id_supervisor dependendo do perfil

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled = true;

    public enum PerfilUsuario {
        ALUNO, DOCENTE, SUPERVISOR, ADMIN
    }
}