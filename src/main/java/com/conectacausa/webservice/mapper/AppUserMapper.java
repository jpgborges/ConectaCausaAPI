package com.conectacausa.webservice.mapper;

import com.conectacausa.webservice.dto.AppUserDTO;
import com.conectacausa.webservice.dto.ZipCodeDTO;
import com.conectacausa.webservice.dto.AbilityDTO;
import com.conectacausa.webservice.model.AppUser;
import com.conectacausa.webservice.model.UserAbility;
import com.conectacausa.webservice.model.ZipCode;

import java.util.List;
import java.util.stream.Collectors;

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

        // 👉 Mapeia as abilities do user
        List<AbilityDTO> abilityDTOs = user.getUserAbilities()
                .stream()
                .map(UserAbility::getAbility)
                .map(ability -> new AbilityDTO(
                        ability.getId(),
                        ability.getDescription()
                ))
                .collect(Collectors.toList());

        return new AppUserDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getAvailabilityStartTime(),
                user.getAvailabilityEndTime(),
                user.getAddressNumber(),
                user.getAddressDetail(),
                zipDTO,
                abilityDTOs // <-- adicionado aqui
        );
    }
}
