package com.example.seuprojeto.controller;

import com.example.seuprojeto.dto.auth.LoginRequest;
import com.example.seuprojeto.dto.auth.RegisterRequest;
import com.example.seuprojeto.entity.Usuario;
import com.example.seuprojeto.security.JwtUtils;
import com.example.seuprojeto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    // Renderiza página de login
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html
    }

    // Renderiza página de cadastro
    @GetMapping("/cadastro")
    public String cadastroPage() {
        return "cadastro"; // cadastro.html
    }

    // Processa login via formulário
    @PostMapping("/login")
    public String authenticateUser(@RequestParam String username,
                                   @RequestParam String password,
                                   Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Se quiser gerar JWT para algum uso no frontend, pode gerar aqui:
            String jwt = jwtUtils.generateJwtToken(username);

            // Redireciona para página principal ou dashboard
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("loginError", "Usuário ou senha inválidos");
            return "login";
        }
    }

    // Processa cadastro via formulário
    @PostMapping("/cadastro")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam(required = false) String email,
                               Model model) {
        if (usuarioService.existsByUsername(username)) {
            model.addAttribute("error", "Usuário já existe! Tente outro nome.");
            return "cadastro";
        }

        if (email != null && usuarioService.existsByEmail(email)) {
            model.addAttribute("error", "E-mail já está em uso!");
            return "cadastro";
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setEmail(email != null ? email : "");
        usuario.setPasswordHash(passwordEncoder.encode(password));
        usuario.setPerfil(Usuario.Perfil.USUARIO);
        usuario.setIsEnabled(true);

        usuarioService.criarUsuario(usuario);

        model.addAttribute("success", "Cadastro realizado com sucesso! Faça login.");
        return "login";
    }
}
