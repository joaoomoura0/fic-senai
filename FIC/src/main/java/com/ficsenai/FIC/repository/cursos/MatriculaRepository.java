package com.ficsenai.FIC.repository.matriculas;

import com.ficsenai.FIC.model.matriculas.Matricula;
import com.ficsenai.FIC.model.matriculas.Matricula.StatusMatricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    Optional<Matricula> findByAluno_IdAlunoAndFic_IdFic(Integer idAluno, Integer idFic);
    List<Matricula> findByFic_IdFic(Integer idFic);
    List<Matricula> findByAluno_IdAluno(Integer idAluno);
    long countByFic_IdFic(Integer idFic); // Para contar alunos matriculados em uma FIC
    List<Matricula> findByFic_IdFicAndStatusMatricula(Integer idFic, StatusMatricula status);
}
