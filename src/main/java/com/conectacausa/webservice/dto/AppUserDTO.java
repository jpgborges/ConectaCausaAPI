package com.conectacausa.webservice.dto;

public record AppUserDTO(
        Integer id,
        String email,
        String name,
        String availabilityStartTime,
        String availabilityEndTime,
        String addressNumber,
        String addressDetail,
        ZipCodeDTO zipCode
) {}
