package com.ficsenai.FIC.repository.pessoas;

import com.ficsenai.FIC.model.pessoas.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    Optional<Aluno> findByCpf(String cpf);
    Optional<Aluno> findByEmail(String email);
    List<Aluno> findByStatus(Aluno.StatusPessoa status);
}
