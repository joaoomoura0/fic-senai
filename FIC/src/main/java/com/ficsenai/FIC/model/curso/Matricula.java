package com.ficsenai.FIC.model.curso;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "matriculas")
@Getter
@Setter
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMatricula;

    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_fic", nullable = false)
    private Fic fic;

    @Column(name = "data_matricula", nullable = false)
    private LocalDateTime dataMatricula = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status_matricula", nullable = false, columnDefinition = "ENUM('PENDENTE', 'CONFIRMADA', 'CANCELADA', 'CONCLUIDA') DEFAULT 'PENDENTE'")
    private StatusMatricula statusMatricula = StatusMatricula.PENDENTE;

    public enum StatusMatricula {
        PENDENTE, CONFIRMADA, CANCELADA, CONCLUIDA
    }
}