package com.conectacausa.webservice.repository;

import com.conectacausa.webservice.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório responsável pelo acesso a dados da entidade Usuário.
 * Fornece operações CRUD básicas através do JpaRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
}
