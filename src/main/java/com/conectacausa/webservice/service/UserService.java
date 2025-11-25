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
    public AppUser registerUser(
            String email,
            String password,
            String name,
            String availability_start_time,
            String availability_end_time,
            String address_number,
            String address_detail,
            String zip_code,
            String abilityIds // agora recebe IDs separados por vírgula
    ) {
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

        // 👉 Primeiro salva o usuário
        AppUser savedUser = userRepository.save(appuser);

        // 👉 Converte a string "1,2,5" em List<Integer>
        List<Integer> ids = Arrays.stream(abilityIds.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();

        // 👉 Busca as abilities pelos IDs
        List<Ability> abilityList = abilityRepository.findAllById(ids);

        if (abilityList.size() != ids.size()) {
            throw new RuntimeException("Habilidade inexistente na lista enviada.");
        }

        // 👉 Cria UserAbility para cada ability encontrada
        for (Ability ability : abilityList) {

            UserAbilityId pk = new UserAbilityId();
            pk.setUserId(savedUser.getId());
            pk.setAbilityId(ability.getId());

            UserAbility userAbility = new UserAbility();
            userAbility.setId(pk);
            userAbility.setUser(savedUser);
            userAbility.setAbility(ability);

            userAbilityRepository.save(userAbility);
        }

        return savedUser;
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

