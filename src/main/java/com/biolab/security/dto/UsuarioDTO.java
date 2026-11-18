package com.biolab.security.dto;

import com.biolab.security.entities.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor @Data
public class UsuarioDTO {
    private UUID id;
    private String nome;
    @Email
    private String email;
    @Size(min = 6,message = "A senha deve ter no minimo 6 caracteres")
    private String senha;
    private Role role;
    @Size(min = 11, max =11)
    private String cpf;

    public UsuarioDTO(String nome, String email, String senha, String cpf, Role role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.cpf = cpf;
    }
}