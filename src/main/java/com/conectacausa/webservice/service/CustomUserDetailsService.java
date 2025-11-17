package com.conectacausa.webservice.service;

import com.conectacausa.webservice.model.AppUser;
import com.conectacausa.webservice.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por carregar detalhes do usuário para autenticação.
 * Implementa a interface UserDetailsService do Spring Security.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Construtor com injeção do repositório de usuários.
     *
     * @param userRepository repositório para acesso a dados de usuários
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Carrega os detalhes de um usuário pelo username.
     *
     * @param username nome de usuário
     * @return objeto UserDetails com informações do usuário
     * @throws UsernameNotFoundException se o usuário não for encontrado
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appuser = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return User.builder()
                .username(appuser.getEmail())
                .password(appuser.getPassword())
                .roles("USER")
                .build();
    }
}

