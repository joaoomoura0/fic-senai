package com.example.FIC.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Integer id;
    private String username;
    private String email;
    private String perfil; // O perfil do usuário
}
