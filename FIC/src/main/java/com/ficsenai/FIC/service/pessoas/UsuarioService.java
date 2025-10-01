package com.example.FIC.service.pessoas;

import com.example.seuprojeto.entity.Usuario;
import com.example.seuprojeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList; // Para as authorities (roles)

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true) // Apenas leitura
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com username: " + username));

        // Aqui você precisa construir um objeto UserDetails do Spring Security.
        // Spring Security oferece uma classe User para isso.

        // Mapear o perfil do seu usuário para uma Authority do Spring Security
        // Ex: "ROLE_ADMIN", "ROLE_ALUNO"
        String role = "ROLE_" + usuario.getPerfil().name();

        return new org.springframework.security.core.userdetails.User(
                usuario.getUsername(),
                usuario.getPasswordHash(),
                usuario.getIsEnabled(), // enabled
                true, // accountNonExpired
                true, // credentialsNonExpired
                true, // accountNonLocked
                // As authorities (roles) do usuário
                java.util.Collections.singletonList(new org.springframework.security.core.authority.SimpleGrantedAuthority(role))
        );
    }

    @Transactional
    public Usuario criarUsuario(Usuario usuario) {
        // Lógica de negócio para criação de usuário, como validar unicidade,
        // criptografar a senha, etc.
        // A senha deve ser criptografada ANTES de chamar save().
        // Este método será usado pelo AuthController para registrar novos usuários.
        return usuarioRepository.save(usuario);
    }

    // Você pode adicionar mais métodos de serviço aqui, como buscar por ID, atualizar, etc.
}