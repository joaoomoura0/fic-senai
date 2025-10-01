package com.ficsenai.FIC.repository.referencias;

import com.ficsenai.FIC.model.referencias.Precificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrecificacaoRepository extends JpaRepository<Precificacao, Integer> {
    Optional<Precificacao> findByDescricao(String descricao);
}
