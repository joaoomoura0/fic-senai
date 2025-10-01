package com.ficsenai.FIC.model.referencias;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "entidades")
@Getter
@Setter
public class Entidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntidade;

    @Column(name = "nome_entidade", nullable = false, length = 50, unique = true)
    private String nomeEntidade;
}