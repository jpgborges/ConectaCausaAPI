package com.conectacausa.webservice.service;

import com.conectacausa.webservice.model.CauseType;
import com.conectacausa.webservice.model.Opportunity;
import com.conectacausa.webservice.model.Organization;
import com.conectacausa.webservice.repository.CauseTypeRepository;
import com.conectacausa.webservice.repository.OpportunityRepository;
import com.conectacausa.webservice.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final OrganizationRepository organizationRepository;
    private final CauseTypeRepository causeTypeRepository;


    public OpportunityService(OpportunityRepository opportunityRepository, OrganizationRepository organizationRepository, CauseTypeRepository causeTypeRepository) {
        this.opportunityRepository = opportunityRepository;
        this.organizationRepository = organizationRepository;
        this.causeTypeRepository = causeTypeRepository;
    }

    public Opportunity registerOpportunity(String description,
                                           String hour,
                                           Integer duration,
                                           Integer organizationId,
                                           Integer causeTypeId) {

        Opportunity opportunity = new Opportunity();

        opportunity.setDescription(description);
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

    public List<Opportunity> findAll() {
        return opportunityRepository.findAll();
    }

}
