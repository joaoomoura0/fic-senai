package com.ficsenai.FIC.model.referencias;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "precificacoes")
@Getter
@Setter
public class PrecificacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrecificacao;

    @Column(nullable = false, length = 50, unique = true)
    private String descricao;
}