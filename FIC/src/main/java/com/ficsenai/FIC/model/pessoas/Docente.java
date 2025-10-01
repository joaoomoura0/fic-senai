package com.ficsenai.FIC.model.pessoas;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "docentes")
@Getter
@Setter
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDocente;

    @Column(name = "nome_docente", nullable = false, length = 100)
    private String nomeDocente;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 14, unique = true)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('ATIVO', 'INATIVO') DEFAULT 'ATIVO'")
    private StatusPessoa status = StatusPessoa.ATIVO;

    @ManyToOne
    @JoinColumn(name = "id_funcao", nullable = false)
    private Funcao funcao;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_entidade", nullable = false)
    private Entidade entidade;

    @ManyToOne
    @JoinColumn(name = "id_filial", nullable = false)
    private Filial filial;

    @ManyToOne
    @JoinColumn(name = "id_superior_imediato")
    private SuperiorImediato superiorImediato;

    public enum StatusPessoa {
        ATIVO, INATIVO
    }
}