package com.conectacausa.webservice.service;

import com.conectacausa.webservice.model.*;
import com.conectacausa.webservice.repository.*;
import com.conectacausa.webservice.view.OpportunityExtended;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final OrganizationRepository organizationRepository;
    private final CauseTypeRepository causeTypeRepository;
    private final AbilityRepository abilityRepository;
    private final OpportunityAbilityRepository opportunityAbilityRepository;


    public OpportunityService(OpportunityRepository opportunityRepository, OrganizationRepository organizationRepository, CauseTypeRepository causeTypeRepository,  AbilityRepository abilityRepository,  OpportunityAbilityRepository opportunityAbilityRepository) {
        this.opportunityRepository = opportunityRepository;
        this.organizationRepository = organizationRepository;
        this.causeTypeRepository = causeTypeRepository;
        this.abilityRepository = abilityRepository;
        this.opportunityAbilityRepository = opportunityAbilityRepository;
    }

    private List<Integer> parseAbilities(String abilities) {
        if (abilities == null || abilities.isBlank()) {
            return Collections.emptyList();
        }

        return Arrays.stream(abilities.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }

    public OpportunityExtended registerOpportunity(
            String description,
            String hour,
            Integer duration,
            Integer organizationId,
            Integer causeTypeId,
            String abilities
    ) {

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

        // Salva a oportunidade
        Opportunity savedOpportunity = opportunityRepository.save(opportunity);

        // Parse das abilities (IDs)
        List<Integer> abilityIds = parseAbilities(abilities);

        // Lista final de descrições das abilities
        List<String> abilityDescriptions = new java.util.ArrayList<>();

        for (Integer abilityId : abilityIds) {

            Ability ability = abilityRepository.findById(abilityId)
                    .orElseThrow(() -> new RuntimeException("Habilidade não encontrada: " + abilityId));

            OpportunityAbilityId compositeId = new OpportunityAbilityId();
            compositeId.setOpportunityId(savedOpportunity.getId());
            compositeId.setAbilityId(abilityId);

            OpportunityAbility relation = new OpportunityAbility();
            relation.setId(compositeId);
            relation.setOpportunity(savedOpportunity);
            relation.setAbility(ability);

            opportunityAbilityRepository.save(relation);

            // salva as descriptions para retornar no Extended
            abilityDescriptions.add(ability.getDescription());
        }

        // Retorna o objeto estendido
        return new OpportunityExtended(savedOpportunity, abilityDescriptions);
    }


    public List<OpportunityExtended> findAll() {

        List<Opportunity> list = opportunityRepository.findAll();

        return list.stream()
                .map(op -> {
                    List<String> abilities = opportunityAbilityRepository.findAllByOpportunityId(op.getId())
                            .stream()
                            .map(oa -> oa.getAbility().getDescription())
                            .toList();

                    return new OpportunityExtended(op, abilities);
                })
                .toList();
    }


}
