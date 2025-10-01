package com.example.seuprojeto.repository;

import com.example.seuprojeto.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {
    Optional<Turma> findByNomeTurma(String nomeTurma);
}