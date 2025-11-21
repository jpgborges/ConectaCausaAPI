package com.conectacausa.webservice.service;

import com.conectacausa.webservice.model.CauseType;
import com.conectacausa.webservice.model.Opportunity;
import com.conectacausa.webservice.model.Organization;
import com.conectacausa.webservice.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

@Service
public class OpportunityRepository {

    private final OpportunityRepository opportunityRepository;
    private final OrganizationRepository organizationRepository;
    private final CauseType causeType;

    public OpportunityRepository(OpportunityRepository opportunityRepository,  OrganizationRepository organizationRepository, CauseType causeType) {
        this.opportunityRepository = opportunityRepository;
        this.organizationRepository = organizationRepository;
        this.causeType = causeType;
    }

    public Opportunity registerOpportunity(String description,
                                           String cep,
                                           String hour,
                                           Integer duration,
                                           Integer organizationId,
                                           Integer causeTypeId) {

        Opportunity opportunity = new Opportunity();

        opportunity.setDescription(description);
        opportunity.setCep(cep);
        opportunity.setHour(hour);
        opportunity.setDuration(duration);

        // Busca da Organization
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new RuntimeException("Organização não encontrada: " + organizationId));
        opportunity.setOrganization(organization);

        // Busca do CauseType
        CauseType causeType = causeTypeRepository.findById(causeTypeId)
                .orElseThrow(() -> new RuntimeException("Tipo de causa não encontrado: " + causeTypeId));
        opportunity.setCauseType(causeType);

        return opportunityRepository.save(opportunity);
    }

}
