package com.ficsenai.FIC.model.curso;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@IdClass(Curso.CursoId.class) // Usamos IdClass para chave composta
public class Curso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCurso;

    @Id
    @Column(name = "versao_curso", nullable = false, length = 20)
    private String versaoCurso;

    @Column(name = "nome_curso", nullable = false, length = 255)
    private String nomeCurso;

    @ManyToOne
    @JoinColumn(name = "id_area_atuacao", nullable = false)
    private AreaAtuacao areaAtuacao;

    @Column(name = "carga_horaria", precision = 6, scale = 2, nullable = false)
    private BigDecimal cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "id_modalidade_ensino", nullable = false)
    private ModalidadeEnsino modalidadeEnsino;

    @ManyToOne
    @JoinColumn(name = "id_precificacao", nullable = false)
    private Precificacao precificacao;

    @Column(name = "valor_original", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorOriginal;

    @Column(name = "valor_atual", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorAtual;

    // Classe para a chave primária composta
    @Getter
    @Setter
    public static class CursoId implements Serializable {
        private Integer idCurso;
        private String versaoCurso;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CursoId cursoId = (CursoId) o;
            return idCurso.equals(cursoId.idCurso) && versaoCurso.equals(cursoId.versaoCurso);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(idCurso, versaoCurso);
        }
    }
}