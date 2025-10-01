package com.ficsenai.FIC.repository.cursos;

import com.ficsenai.FIC.model.curso.Ambiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmbienteRepository extends JpaRepository<Ambiente, Integer> {
    Optional<Ambiente> findByNomeAmbiente(String nomeAmbiente);
}
