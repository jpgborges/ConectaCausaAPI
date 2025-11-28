package com.conectacausa.webservice.dto;

import java.util.List;

public record OpportunityDTO(
        Integer id,
        String description,
        String hour,
        Integer duration,
        OrganizationDTO organization,
        CauseTypeDTO causeType,
        List<String> abilities
) {}

