package com.conectacausa.webservice.service;

import com.conectacausa.webservice.model.Organization;
import com.conectacausa.webservice.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization registerOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }
}
