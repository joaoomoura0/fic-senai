package com.ficsenai.FIC.model.referencias;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "modalidades_ensino")
@Getter
@Setter
public class ModalidadeEnsino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idModalidadeEnsino;

    @Column(nullable = false, length = 100, unique = true)
    private String descricao;
}