package com.conectacausa.webservice.service;

import com.conectacausa.webservice.model.AppUser;
import com.conectacausa.webservice.model.ZipCode;
import com.conectacausa.webservice.repository.UserRepository;
import com.conectacausa.webservice.repository.ZipCodeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Serviço responsável pela lógica de negócio relacionada a usuários.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ZipCodeRepository zipCodeRepository;


    /**
     * Construtor com injeção do repositório de usuários.
     * Inicializa o encoder de senhas BCrypt.
     *
     * @param userRepository repositório para acesso a dados de usuários
     */
    public UserService(UserRepository userRepository, ZipCodeRepository zipCodeRepository){
        this.userRepository = userRepository;
        this.zipCodeRepository = zipCodeRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Registra um novo usuário com senha criptografada.
     *
     * @param username nome de usuário
     * @param password senha do usuário
     * @return usuário registrado
     */
    public AppUser registerUser(String email, String password, String name, String availability_start_time, String availability_end_time, String address_number, String address_detail, String zip_code){
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

        return userRepository.save(appuser);
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

