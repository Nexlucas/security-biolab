package com.biolab.security.service;

import com.biolab.security.entities.Usuario;
import com.biolab.security.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;
    public CustomUserDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Usuario usuario = repository.findByCpf(cpf);
        return User.builder()
                .username(usuario.getCpf())
                .password(usuario.getSenha())
                .roles(usuario.getRole().name().replace("ROLE_",""))
                .build();
    }
}