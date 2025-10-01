package com.example.FIC.dto.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "O username não pode ser vazio.")
    @Size(min = 3, max = 20, message = "O username deve ter entre 3 e 20 caracteres.")
    private String username;

    @NotBlank(message = "O e-mail não pode ser vazio.")
    @Size(max = 100, message = "O e-mail não pode exceder 100 caracteres.")
    @Email(message = "Formato de e-mail inválido.")
    private String email;

    @NotBlank(message = "A senha não pode ser vazia.")
    @Size(min = 6, max = 40, message = "A senha deve ter entre 6 e 40 caracteres.")
    private String password;

    // Campos adicionais podem ser adicionados posteriormente, por exemplo:
    // private String perfil;
}
