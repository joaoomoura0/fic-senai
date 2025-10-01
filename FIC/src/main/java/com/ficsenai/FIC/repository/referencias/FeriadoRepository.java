package com.ficsenai.FIC.repository.referencias;

import com.ficsenai.FIC.model.referencias.Feriado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface FeriadoRepository extends JpaRepository<Feriado, Integer> {
    Optional<Feriado> findByDataFeriado(LocalDate dataFeriado);
}
