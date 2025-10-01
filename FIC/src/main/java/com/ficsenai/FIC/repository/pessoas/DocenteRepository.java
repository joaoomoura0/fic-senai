package com.ficsenai.FIC.repository.pessoas;

import com.ficsenai.FIC.model.pessoas.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {
    Optional<Docente> findByCpf(String cpf);
    Optional<Docente> findByEmail(String email);
    List<Docente> findByStatus(Docente.StatusPessoa status);
}
