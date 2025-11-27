package com.conectacausa.webservice.controller;

import com.conectacausa.webservice.dto.AppUserDTO;
import com.conectacausa.webservice.mapper.AppUserMapper;
import com.conectacausa.webservice.model.AppUser;
import com.conectacausa.webservice.service.UserService;
import com.conectacausa.webservice.view.AppUserExtended;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<AppUserDTO> getProfile() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        AppUserExtended user = userService.findUserWithAbilities(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return ResponseEntity.ok(AppUserMapper.toDTO(user));
    }

}
