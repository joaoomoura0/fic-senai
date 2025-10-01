package com.ficsenai.FIC.model.curso;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ofertas_curso")
@Getter
@Setter
public class OfertaCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOferta;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_curso", referencedColumnName = "id_curso", nullable = false),
            @JoinColumn(name = "versao_curso", referencedColumnName = "versao_curso", nullable = false)
    })
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_turma", nullable = false)
    private Turma turma;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_termino", nullable = false)
    private LocalDate dataTermino;

    @Column(name = "horario_inicio_previsto", nullable = false)
    private LocalTime horarioInicioPrevisto;

    @Column(name = "horario_termino_previsto", nullable = false)
    private LocalTime horarioTerminoPrevisto;

    @OneToMany(mappedBy = "oferta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HorarioOfertaCurso> horarios = new HashSet<>();
}