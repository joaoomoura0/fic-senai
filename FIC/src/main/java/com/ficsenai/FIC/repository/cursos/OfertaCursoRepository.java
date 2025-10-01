package com.ficsenai.FIC.repository.cursos;

import com.ficsenai.FIC.model.curso.OfertaCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OfertaCursoRepository extends JpaRepository<OfertaCurso, Integer> {
    List<OfertaCurso> findByDataInicioBetween(LocalDate startDate, LocalDate endDate);
    List<OfertaCurso> findByCurso_IdCursoAndCurso_VersaoCurso(Integer idCurso, String versaoCurso);
    Optional<OfertaCurso> findByTurma_IdTurma(Integer idTurma); // Se uma turma só pode ter 1 oferta
    boolean existsByTurma_IdTurma(Integer idTurma);
}
