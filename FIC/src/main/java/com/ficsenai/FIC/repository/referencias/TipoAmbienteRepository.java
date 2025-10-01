package com.ficsenai.FIC.repository.referencias;

import com.ficsenai.FIC.model.referencias.TipoAmbiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoAmbienteRepository extends JpaRepository<TipoAmbiente, Integer> {
    Optional<TipoAmbiente> findByDescricao(String descricao);
}
