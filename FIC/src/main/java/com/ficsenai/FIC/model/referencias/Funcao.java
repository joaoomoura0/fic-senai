package com.ficsenai.FIC.model.referencias;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "funcoes")
@Getter
@Setter
public class FuncaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFuncao;

    @Column(nullable = false, length = 50, unique = true)
    private String descricao;
}