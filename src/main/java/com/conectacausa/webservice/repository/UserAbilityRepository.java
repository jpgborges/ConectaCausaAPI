package com.conectacausa.webservice.repository;

import com.conectacausa.webservice.model.UserAbility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAbilityRepository extends JpaRepository<UserAbility, Integer> {

    Optional<UserAbility> findById(Integer id);
}
