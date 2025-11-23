package com.conectacausa.webservice.dto;

public record OpportunityDTO(
        Integer id,
        String description,
        String hour,
        Integer duration,
        OrganizationDTO organization,
        CauseTypeDTO causeType
) {}

