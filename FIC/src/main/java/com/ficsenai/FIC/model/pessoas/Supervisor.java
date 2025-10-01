package com.ficsenai.FIC.model.pessoas;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "supervisores")
@Getter
@Setter
public class Supervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSupervisor;

    @Column(name = "nome_supervisor", nullable = false, length = 100)
    private String nomeSupervisor;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 14, unique = true)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('ATIVO', 'INATIVO') DEFAULT 'ATIVO'")
    private StatusPessoa status = StatusPessoa.ATIVO;

    public enum StatusPessoa {
        ATIVO, INATIVO
    }
}