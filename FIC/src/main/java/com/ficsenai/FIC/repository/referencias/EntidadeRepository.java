package com.ficsenai.FIC.repository.referencias;

import com.ficsenai.FIC.model.referencias.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Integer> {
    Optional<Entidade> findByNomeEntidade(String nomeEntidade);
}
