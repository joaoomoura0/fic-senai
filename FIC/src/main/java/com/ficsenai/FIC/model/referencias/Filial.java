package com.ficsenai.FIC.model.referencias;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "filiais")
@Getter
@Setter
public class Filial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFilial;

    @Column(name = "nome_filial", nullable = false, length = 100, unique = true)
    private String nomeFilial;
}