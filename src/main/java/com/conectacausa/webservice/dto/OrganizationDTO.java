package com.conectacausa.webservice.dto;

public record OrganizationDTO(
        Integer id,
        String name,
        String email,
        String addressNumber,
        String addressDetail,
        ZipCodeDTO zipCode
) {}
