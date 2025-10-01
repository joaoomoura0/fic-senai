package com.ficsenai.FIC.model.pessoas;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "alunos")
@Getter
@Setter
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAluno;

    @Column(name = "nome_aluno", nullable = false, length = 100)
    private String nomeAluno;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 14, unique = true)
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(length = 255)
    private String endereco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('ATIVO', 'INATIVO') DEFAULT 'ATIVO'")
    private StatusPessoa status = StatusPessoa.ATIVO;

    public enum StatusPessoa {
        ATIVO, INATIVO
    }
}