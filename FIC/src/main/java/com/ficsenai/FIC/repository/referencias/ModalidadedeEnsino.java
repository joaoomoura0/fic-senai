package com.ficsenai.FIC.repository.referencias;

import com.ficsenai.FIC.model.referencias.ModalidadeEnsino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModalidadeEnsinoRepository extends JpaRepository<ModalidadeEnsino, Integer> {
    Optional<ModalidadeEnsino> findByDescricao(String descricao);
}
