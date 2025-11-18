package com.conectacausa.webservice.mapper;

import com.conectacausa.webservice.dto.AppUserDTO;
import com.conectacausa.webservice.dto.ZipCodeDTO;
import com.conectacausa.webservice.model.AppUser;
import com.conectacausa.webservice.model.ZipCode;

public class AppUserMapper {

    public static AppUserDTO toDTO(AppUser user) {

        ZipCode zip = user.getZipCode();

        ZipCodeDTO zipDTO = new ZipCodeDTO(
                zip.getZipCode(),
                zip.getStreet(),
                zip.getNeighborhood(),
                zip.getCity(),
                zip.getStateCode(),
                zip.getCountryCode()
        );

        return new AppUserDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getAvailabilityStartTime(),
                user.getAvailabilityEndTime(),
                user.getAddressNumber(),
                user.getAddressDetail(),
                zipDTO
        );
    }
}
