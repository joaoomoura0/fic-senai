package com.ficsenai.FIC.repository.cursos;

import com.ficsenai.FIC.model.curso.Fic;
import com.ficsenai.FIC.model.curso.StatusFic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FicRepository extends JpaRepository<Fic, Integer> {
    Optional<Fic> findByOferta_IdOferta(Integer idOferta); // Para garantir a unicidade da oferta na FIC
    List<Fic> findBySupervisor_IdSupervisor(Integer idSupervisor);
    List<Fic> findByDocentePrincipal_IdDocente(Integer idDocente);
    List<Fic> findByDocenteSecundario_IdDocente(Integer idDocente);
    List<Fic> findByStatusFic(StatusFic statusFic);
}
