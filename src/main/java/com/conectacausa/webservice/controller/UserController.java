package com.conectacausa.webservice.controller;

import com.conectacausa.webservice.dto.AppUserDTO;
import com.conectacausa.webservice.mapper.AppUserMapper;
import com.conectacausa.webservice.view.AppUserExtended;
import com.conectacausa.webservice.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

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

    @Operation(
            summary = "Obter perfil do usuário autenticado",
            description = """
                Retorna os dados do usuário atualmente autenticado, incluindo informações 
                pessoais e habilidades cadastradas.

                O e-mail do usuário é extraído automaticamente a partir do token JWT 
                presente na requisição (via SecurityContextHolder).
                """,
            security = { @SecurityRequirement(name = "bearerAuth") },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Perfil retornado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppUserDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Token JWT ausente, inválido ou expirado"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário não encontrado"
                    )
            }
    )
    @GetMapping("/me")
    public ResponseEntity<AppUserDTO> getProfile() {

        var auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        AppUserExtended user = userService.findUserWithAbilities(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return ResponseEntity.ok(AppUserMapper.toDTO(user));
    }
}
