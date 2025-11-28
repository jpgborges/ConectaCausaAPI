package com.conectacausa.webservice.repository;

import com.conectacausa.webservice.model.UserOpportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOpportunityRepository extends JpaRepository<UserOpportunity, Integer> {
}
