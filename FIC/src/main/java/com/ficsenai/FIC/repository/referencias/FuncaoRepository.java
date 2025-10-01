package com.ficsenai.FIC.repository.referencias; // Agora vai para o pacote de repositórios de referências

import com.ficsenai.FIC.model.referencias.Funcao; // Ajuste para o pacote correto da entidade
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Integer> {
    Optional<Funcao> findByDescricao(String descricao);
}
