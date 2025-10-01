package com.ficsenai.FIC.repository.cursos;

import com.ficsenai.FIC.model.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Curso.CursoId> { // Chave primária composta
    Optional<Curso> findByNomeCursoAndVersaoCurso(String nomeCurso, String versaoCurso);
    List<Curso> findByNomeCursoContainingIgnoreCase(String nomeCurso);
}
