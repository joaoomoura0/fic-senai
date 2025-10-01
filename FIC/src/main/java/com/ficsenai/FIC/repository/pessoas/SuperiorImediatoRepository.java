package com.example.seuprojeto.repository;

import com.example.seuprojeto.entity.SuperiorImediato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperiorImediatoRepository extends JpaRepository<SuperiorImediato, Integer> {
    Optional<SuperiorImediato> findByCpf(String cpf);
    Optional<SuperiorImediato> findByEmail(String email);
}