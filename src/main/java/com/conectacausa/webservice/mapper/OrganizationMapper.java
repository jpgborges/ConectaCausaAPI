package com.conectacausa.webservice.mapper;

import com.conectacausa.webservice.dto.OrganizationDTO;
import com.conectacausa.webservice.dto.ZipCodeDTO;
import com.conectacausa.webservice.model.Organization;
import com.conectacausa.webservice.model.ZipCode;

public class OrganizationMapper {

    public static OrganizationDTO toDTO(Organization organization) {

        ZipCode zip = organization.getZipCode();

        ZipCodeDTO zipDTO = new ZipCodeDTO(
                zip.getZipCode(),
                zip.getStreet(),
                zip.getNeighborhood(),
                zip.getCity(),
                zip.getStateCode(),
                zip.getCountryCode()
        );

        return new OrganizationDTO(
                organization.getId(),
                organization.getName(),
                organization.getEmail(),
                organization.getAddressNumber(),
                organization.getAddressDetail(),
                zipDTO
        );
    }
}