package com.ficsenai.FIC.model.referencias;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "status_fic")
@Getter
@Setter
public class StatusFic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatusFic;

    @Column(nullable = false, length = 50, unique = true)
    private String descricao;
}