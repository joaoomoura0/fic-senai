package com.ficsenai.FIC.model.referencias;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "feriados")
@Getter
@Setter
public class Feriado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFeriado;

    @Column(name = "data_feriado", nullable = false, unique = true)
    private LocalDate dataFeriado;

    @Column(nullable = false, length = 100)
    private String descricao;
}