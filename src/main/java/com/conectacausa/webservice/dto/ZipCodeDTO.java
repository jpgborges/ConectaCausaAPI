package com.conectacausa.webservice.dto;

public record ZipCodeDTO(
        String zipCode,
        String street,
        String neighborhood,
        String city,
        String state,
        String countryCode
) {}
