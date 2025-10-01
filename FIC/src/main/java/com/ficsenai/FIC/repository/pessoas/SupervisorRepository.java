package com.ficsenai.FIC.repository.pessoas;

import com.ficsenai.FIC.model.pessoas.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {
    Optional<Supervisor> findByCpf(String cpf);
    Optional<Supervisor> findByEmail(String email);
    List<Supervisor> findByStatus(Supervisor.StatusPessoa status);
}
