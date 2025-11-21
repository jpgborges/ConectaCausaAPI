package com.conectacausa.webservice.repository;

import com.conectacausa.webservice.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OpportunityRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findOrganizationByName(String name);

}
