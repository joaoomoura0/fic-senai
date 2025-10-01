package com.ficsenai.FIC.model.pessoas;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "superiores_imediatos")
@Getter
@Setter
public class SuperiorImediato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSuperiorImediato;

    @Column(name = "nome_superior", nullable = false, length = 100)
    private String nomeSuperior;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 14, unique = true)
    private String cpf;
}