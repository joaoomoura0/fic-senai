package com.ficsenai.FIC.model.referencias;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tipos_ambiente")
@Getter
@Setter
public class TipoAmbiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoAmbiente;

    @Column(nullable = false, length = 50, unique = true)
    private String descricao;
}