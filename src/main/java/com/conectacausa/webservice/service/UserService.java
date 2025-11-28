package com.conectacausa.webservice.service;

import com.conectacausa.webservice.model.*;
import com.conectacausa.webservice.repository.AbilityRepository;
import com.conectacausa.webservice.repository.UserAbilityRepository;
import com.conectacausa.webservice.repository.UserRepository;
import com.conectacausa.webservice.repository.ZipCodeRepository;
import com.conectacausa.webservice.view.AppUserExtended;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ZipCodeRepository zipCodeRepository;
    private final AbilityRepository  abilityRepository;
    private final UserAbilityRepository userAbilityRepository;

    public UserService(UserRepository userRepository, ZipCodeRepository zipCodeRepository, AbilityRepository abilityRepository, UserAbilityRepository userAbilityRepository) {
        this.userRepository = userRepository;
        this.zipCodeRepository = zipCodeRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.abilityRepository = abilityRepository;
        this.userAbilityRepository = userAbilityRepository;
    }

    public AppUser registerUser(String email, String password, String name,
                                String availability_start_time, String availability_end_time,
                                String address_number, String address_detail,
                                String zip_code, String abilities) {

        String encodedPassword = passwordEncoder.encode(password);
        AppUser appuser = new AppUser();
        appuser.setEmail(email);
        appuser.setPassword(encodedPassword);
        appuser.setName(name);
        appuser.setAvailabilityStartTime(availability_start_time);
        appuser.setAvailabilityEndTime(availability_end_time);
        appuser.setAddressNumber(address_number);
        appuser.setAddressDetail(address_detail);

        ZipCode zipcodeObj = zipCodeRepository.findByZipCode(zip_code)
                .orElseThrow(() -> new RuntimeException("ZipCode não encontrado: " + zip_code));

        appuser.setZipCode(zipcodeObj);

        AppUser savedUser = userRepository.save(appuser);

        List<Integer> abilityIds = parseAbilities(abilities);

        for (Integer abilityId : abilityIds) {

            Ability ability = abilityRepository.findById(abilityId)
                    .orElseThrow(() -> new RuntimeException("Ability não encontrada: " + abilityId));

            UserAbilityId compositeId = new UserAbilityId();
            compositeId.setUserId(savedUser.getId());
            compositeId.setAbilityId(abilityId);


            UserAbility relation = new UserAbility();
            relation.setId(compositeId);
            relation.setUser(savedUser);
            relation.setAbility(ability);

            userAbilityRepository.save(relation);
        }

        return savedUser;
    }


    private List<Integer> parseAbilities(String abilities) {
        if (abilities == null || abilities.isBlank()) {
            return Collections.emptyList();
        }

        return Arrays.stream(abilities.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }

    public Optional<AppUserExtended> findUserWithAbilities(String email) {

        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<String> abilities = userAbilityRepository.findAllByUserId(user.getId())
                .stream()
                .map(ua -> ua.getAbility().getDescription())
                .toList();

        return Optional.of(new AppUserExtended(user, abilities));
    }


    public Optional<AppUser> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

}

