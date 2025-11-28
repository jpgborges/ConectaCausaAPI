package com.conectacausa.webservice.repository;

import com.conectacausa.webservice.model.UserAbility;
import com.conectacausa.webservice.model.UserAbilityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAbilityRepository extends JpaRepository<UserAbility, UserAbilityId> {

    List<UserAbility> findAllByUserId(Integer userId);

}
