package com.ficsenai.FIC.repository.referencias;

import com.ficsenai.FIC.model.referencias.AreaAtuacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaAtuacaoRepository extends JpaRepository<AreaAtuacao, Integer> {
    Optional<AreaAtuacao> findByDescricao(String descricao);
}
