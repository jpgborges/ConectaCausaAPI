package com.conectacausa.webservice.service;

import com.conectacausa.webservice.model.*;
import com.conectacausa.webservice.repository.AbilityRepository;
import com.conectacausa.webservice.repository.UserAbilityRepository;
import com.conectacausa.webservice.repository.UserRepository;
import com.conectacausa.webservice.repository.ZipCodeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pela lógica de negócio relacionada a usuários.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ZipCodeRepository zipCodeRepository;
    private final AbilityRepository  abilityRepository;
    private final UserAbilityRepository userAbilityRepository;


    /**
     * Construtor com injeção do repositório de usuários.
     * Inicializa o encoder de senhas BCrypt.
     *
     * @param userRepository repositório para acesso a dados de usuários
     */
    public UserService(UserRepository userRepository, ZipCodeRepository zipCodeRepository, AbilityRepository abilityRepository, UserAbilityRepository userAbilityRepository) {
        this.userRepository = userRepository;
        this.zipCodeRepository = zipCodeRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.abilityRepository = abilityRepository;
        this.userAbilityRepository = userAbilityRepository;
    }

    /**
     * Registra um novo usuário com senha criptografada.
     *
     * @param username nome de usuário
     * @param password senha do usuário
     * @return usuário registrado
     */
//    public AppUser registerUser(String email, String password, String name, String availability_start_time, String availability_end_time, String address_number, String address_detail, String zip_code, String abilities){
//        String encodedPassword = passwordEncoder.encode(password);
//        AppUser appuser = new AppUser();
//        appuser.setEmail(email);
//        appuser.setPassword(encodedPassword);
//        appuser.setName(name);
//        appuser.setAvailabilityStartTime(availability_start_time);
//        appuser.setAvailabilityEndTime(availability_end_time);
//        appuser.setAddressNumber(address_number);
//        appuser.setAddressDetail(address_detail);
//        ZipCode zipcodeObj = zipCodeRepository.findByZipCode(zip_code)
//                .orElseThrow(() -> new RuntimeException("ZipCode não encontrado: " + zip_code));
//
//        appuser.setZipCode(zipcodeObj);
//
//        return userRepository.save(appuser);
//    }
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

        // 🔥 Salva o usuário primeiro
        AppUser savedUser = userRepository.save(appuser);

        // 🔥 Parseia a string abilities
        List<Integer> abilityIds = parseAbilities(abilities);

        // 🔥 Para cada ID de habilidade -> cria UserAbility
        for (Integer abilityId : abilityIds) {

            Ability ability = abilityRepository.findById(abilityId)
                    .orElseThrow(() -> new RuntimeException("Ability não encontrada: " + abilityId));

            UserAbilityId compositeId = new UserAbilityId(savedUser.getId(), abilityId);

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


    /**
     * Busca um usuário pelo username.
     *
     * @param username nome de usuário
     * @return Optional contendo o usuário, se encontrado
     */
    public Optional<AppUser> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

}

