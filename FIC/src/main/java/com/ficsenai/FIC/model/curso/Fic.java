package com.ficsenai.FIC.model.curso;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fics")
@Getter
@Setter
public class Fic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFic;

    @Column(name = "data_abertura", nullable = false)
    private LocalDate dataAbertura;

    @OneToOne
    @JoinColumn(name = "id_oferta", nullable = false, unique = true)
    private OfertaCurso oferta;

    @ManyToOne
    @JoinColumn(name = "id_supervisor", nullable = false)
    private Supervisor supervisor;

    @ManyToOne
    @JoinColumn(name = "id_ambiente", nullable = false)
    private Ambiente ambiente;

    @Column(name = "num_edital", length = 50)
    private String numEdital;

    @ManyToOne
    @JoinColumn(name = "docente_principal_id", nullable = false)
    private Docente docentePrincipal;

    @ManyToOne
    @JoinColumn(name = "docente_secundario_id")
    private Docente docenteSecundario;

    @Column(name = "nr_minimo_alunos", nullable = false)
    private Integer nrMinimoAlunos;

    @Column(name = "nr_maximo_alunos", nullable = false)
    private Integer nrMaximoAlunos;

    @Column(name = "qtd_parcelas", nullable = false)
    private Integer qtdParcelas;

    @Column(name = "id_matricula_regular", length = 50)
    private String idMatriculaRegular;

    @Column(name = "valor_total_curso", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorTotalCurso;

    @Column(name = "valor_parcela", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorParcela;

    @Column(name = "valor_provisionamento", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorProvisionamento;

    @ManyToOne
    @JoinColumn(name = "id_status_fic", nullable = false)
    private StatusFic statusFic;
}