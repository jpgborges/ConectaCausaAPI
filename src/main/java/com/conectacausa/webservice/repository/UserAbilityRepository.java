package com.conectacausa.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.conectacausa.webservice.model.UserAbility;

@Repository
public interface UserAbilityRepository extends JpaRepository<UserAbility,Integer> {

}
