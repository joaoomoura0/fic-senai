package com.ficsenai.FIC.repository.referencias;

import com.ficsenai.FIC.model.referencias.StatusFic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusFicRepository extends JpaRepository<StatusFic, Integer> {
    Optional<StatusFic> findByDescricao(String descricao);
}
