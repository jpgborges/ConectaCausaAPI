package com.conectacausa.webservice.repository;

import com.conectacausa.webservice.model.Ability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
public interface AbilityRepository extends JpaRepository<Ability, Integer> {

    Optional<Ability> findbyid(Integer id);
}
