package com.conectacausa.webservice.mapper;

import com.conectacausa.webservice.dto.AppUserDTO;
import com.conectacausa.webservice.dto.ZipCodeDTO;
import com.conectacausa.webservice.model.AppUser;
import com.conectacausa.webservice.model.ZipCode;
import com.conectacausa.webservice.view.AppUserExtended;

import java.util.List;

public class AppUserMapper {

    public static AppUserDTO toDTO(AppUserExtended ext) {

        AppUser user = ext.getUser();

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
                zipDTO,
                ext.getAbilities()
        );
    }

}
