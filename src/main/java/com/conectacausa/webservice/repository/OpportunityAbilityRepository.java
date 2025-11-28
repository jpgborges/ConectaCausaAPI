package com.conectacausa.webservice.repository;

import com.conectacausa.webservice.model.Opportunity;
import com.conectacausa.webservice.model.OpportunityAbility;
import com.conectacausa.webservice.model.OpportunityAbilityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityAbilityRepository extends JpaRepository<OpportunityAbility, OpportunityAbilityId> {

    List<OpportunityAbility> findAllByOpportunityId(Integer opportunityId);

}
