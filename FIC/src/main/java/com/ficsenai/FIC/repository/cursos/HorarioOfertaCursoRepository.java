package com.ficsenai.FIC.repository.cursos;

import com.ficsenai.FIC.model.curso.HorarioOfertaCurso;
import com.ficsenai.FIC.model.curso.OfertaCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HorarioOfertaCursoRepository extends JpaRepository<HorarioOfertaCurso, Integer> {
    List<HorarioOfertaCurso> findByOferta(OfertaCurso oferta);
    Optional<HorarioOfertaCurso> findByOfertaAndDiaSemana(OfertaCurso oferta, HorarioOfertaCurso.DiaSemana diaSemana);
}
