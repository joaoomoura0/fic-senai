package com.ficsenai.FIC.model.curso;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ambientes")
@Getter
@Setter
public class Ambiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAmbiente;

    @Column(name = "nome_ambiente", nullable = false, length = 100)
    private String nomeAmbiente;

    @ManyToOne
    @JoinColumn(name = "id_tipo_ambiente", nullable = false)
    private TipoAmbiente tipoAmbiente;
}