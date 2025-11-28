package com.conectacausa.webservice.mapper;

import com.conectacausa.webservice.dto.CauseTypeDTO;
import com.conectacausa.webservice.dto.OpportunityDTO;
import com.conectacausa.webservice.dto.OrganizationDTO;
import com.conectacausa.webservice.dto.ZipCodeDTO;
import com.conectacausa.webservice.model.*;
import com.conectacausa.webservice.view.OpportunityExtended;

public class OpportunityMapper {

    public static OpportunityDTO toDTO(OpportunityExtended ext) {

        Opportunity opportunity = ext.getOpportunity();

        Organization org = opportunity.getOrganization();

        ZipCode zip = org.getZipCode();

        ZipCodeDTO zipDTO = new ZipCodeDTO(
                zip.getZipCode(),
                zip.getStreet(),
                zip.getNeighborhood(),
                zip.getCity(),
                zip.getStateCode(),
                zip.getCountryCode()
        );

        OrganizationDTO organizationDTO = new OrganizationDTO(
                org.getId(),
                org.getName(),
                org.getEmail(),
                org.getAddressNumber(),
                org.getAddressDetail(),
                zipDTO
        );

        CauseType cause = opportunity.getCauseType();

        CauseTypeDTO causeTypeDTO = new CauseTypeDTO(
                cause.getId(),
                cause.getDescription()
        );

        return new OpportunityDTO(
                opportunity.getId(),
                opportunity.getDescription(),
                opportunity.getHour(),
                opportunity.getDuration(),
                organizationDTO,
                causeTypeDTO,
                ext.getAbilities()
        );
    }
}
