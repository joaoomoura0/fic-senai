package com.ficsenai.FIC.model.referencias;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "areas_atuacao")
@Getter
@Setter
public class AreaAtuacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAreaAtuacao;

    @Column(nullable = false, length = 100, unique = true)
    private String descricao;
}