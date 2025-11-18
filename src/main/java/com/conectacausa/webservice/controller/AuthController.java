package com.conectacausa.webservice.controller;


import com.conectacausa.webservice.model.AppUser;
import com.conectacausa.webservice.security.JwtUtil;
import com.conectacausa.webservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * Controlador responsável pelas operações de autenticação.
 * Fornece endpoints para relacionados a autenticação.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    /**
     * Construtor para injeção do serviço de usuários.
     *
     * @param userService serviço de gerenciamento de usuários
     */
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registra um novo usuário no sistema.
     *
     * @param request mapa contendo os campos "username" e "password"
     * @return usuário criado em caso de sucesso
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam("availability_start_time") String availabilityStartTime,
            @RequestParam("availability_end_time") String availabilityEndTime,
            @RequestParam("address_number") String addressNumber,
            @RequestParam("address_detail") String addressDetail,
            @RequestParam("zip_code") String zipCode
    ) {

        AppUser appuser = userService.registerUser(
                email,
                password,
                name,
                availabilityStartTime,
                availabilityEndTime,
                addressNumber,
                addressDetail,
                zipCode
        );

        return ResponseEntity.ok(appuser);
    }


    /**
     * Realiza a autenticação de um usuário.
     * Verifica as credenciais e retorna um token JWT se forem válidas.
     *
     * @param request mapa contendo os campos "username" e "password"
     * @return token JWT em caso de sucesso,
     * ou mensagem de erro com status 401 se inválidas
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Optional<AppUser> user = userService.findByEmail(request.get("email"));
        if (user.isPresent() && new BCryptPasswordEncoder().matches(request.get("password"), user.get().getPassword())) {
            String token = JwtUtil.generateToken(user.get().getEmail().toString());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}