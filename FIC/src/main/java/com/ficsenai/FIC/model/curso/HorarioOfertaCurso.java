package com.ficsenai.FIC.model.curso;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "horarios_oferta_curso")
@Getter
@Setter
public class HorarioOfertaCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHorario;

    @ManyToOne
    @JoinColumn(name = "id_oferta", nullable = false)
    private OfertaCurso oferta;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false, columnDefinition = "ENUM('SEG', 'TER', 'QUA', 'QUI', 'SEX', 'SAB', 'DOM')")
    private DiaSemana diaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @Column(name = "horas_diarias", precision = 4, scale = 2, nullable = false)
    private BigDecimal horasDiarias;

    public enum DiaSemana {
        SEG, TER, QUA, QUI, SEX, SAB, DOM
    }
}