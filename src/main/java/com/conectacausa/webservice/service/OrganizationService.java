package com.conectacausa.webservice.service;

import com.conectacausa.webservice.dto.OrganizationDTO;
import com.conectacausa.webservice.mapper.OrganizationMapper;
import com.conectacausa.webservice.model.Organization;
import com.conectacausa.webservice.model.ZipCode;
import com.conectacausa.webservice.repository.OrganizationRepository;
import com.conectacausa.webservice.repository.ZipCodeRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final ZipCodeRepository zipCodeRepository;

    public OrganizationService(OrganizationRepository organizationRepository,  ZipCodeRepository zipCodeRepository) {
        this.organizationRepository = organizationRepository;
        this.zipCodeRepository = zipCodeRepository;
    }

    public Organization registerOrganization(String name,
                                             String email,
                                             String addressNumber,
                                             String addressDetail,
                                             String zipCode) {

        Organization organization = new Organization();

        organization.setName(name);
        organization.setEmail(email);
        organization.setAddressNumber(addressNumber);
        organization.setAddressDetail(addressDetail);

        ZipCode zipcodeObj = zipCodeRepository.findByZipCode(zipCode)
                .orElseThrow(() -> new RuntimeException("CEP não encontrado: " + zipCode));

        organization.setZipCode(zipcodeObj);

        return organizationRepository.save(organization);
    }

    public OrganizationDTO getOrganizationById(Integer id) {

        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organização não encontrada: " + id));

        return OrganizationMapper.toDTO(organization);
    }

    public Organization updateOrganization(
            Integer id,
            String name,
            String email,
            String addressNumber,
            String addressDetail,
            String zipCode
    ) {

        Organization org = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization não encontrada: " + id));

        org.setName(name);
        org.setEmail(email);
        org.setAddressNumber(addressNumber);
        org.setAddressDetail(addressDetail);

        ZipCode zip = zipCodeRepository.findByZipCode(zipCode)
                .orElseThrow(() -> new RuntimeException("ZipCode não encontrado: " + zipCode));

        org.setZipCode(zip);

        return organizationRepository.save(org);
    }

}
