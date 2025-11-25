package com.conectacausa.webservice.repository;

import com.conectacausa.webservice.model.Ability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityRepository extends JpaRepository<Ability, Integer> {
}
