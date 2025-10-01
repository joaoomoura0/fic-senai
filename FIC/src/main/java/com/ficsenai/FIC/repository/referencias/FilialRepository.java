package com.ficsenai.FIC.repository.referencias;

import com.ficsenai.FIC.model.referencias.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Integer> {
    Optional<Filial> findByNomeFilial(String nomeFilial);
}