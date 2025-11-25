package com.conectacausa.webservice.dto;

import java.util.List;

public record AppUserDTO(
        Integer id,
        String email,
        String name,
        String availabilityStartTime,
        String availabilityEndTime,
        String addressNumber,
        String addressDetail,
        ZipCodeDTO zipCode,
        List<Integer> abilities
) {}
