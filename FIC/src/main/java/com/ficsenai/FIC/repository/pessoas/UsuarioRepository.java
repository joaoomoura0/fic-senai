package com.ficsenai.FIC.repository.pessoas;

import com.ficsenai.FIC.model.pessoas.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByIdReferenciaAndPerfil(Integer idReferencia, Usuario.PerfilUsuario perfil);
}
