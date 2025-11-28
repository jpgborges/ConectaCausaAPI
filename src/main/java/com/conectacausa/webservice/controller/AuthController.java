package com.conectacausa.webservice.controller;

import com.conectacausa.webservice.model.AppUser;
import com.conectacausa.webservice.security.JwtUtil;
import com.conectacausa.webservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Registrar novo usuário",
            description = "Cria um novo usuário voluntário na plataforma ConectaCausa"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppUser.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Parameter(description = "E-mail do usuário", required = true)
            @RequestParam String email,

            @Parameter(description = "Senha do usuário", required = true)
            @RequestParam String password,

            @Parameter(description = "Nome completo", required = true)
            @RequestParam String name,

            @Parameter(description = "Horário de início da disponibilidade (ex: 08:00)", required = true)
            @RequestParam("availability_start_time") String availabilityStartTime,

            @Parameter(description = "Horário de fim da disponibilidade (ex: 18:00)", required = true)
            @RequestParam("availability_end_time") String availabilityEndTime,

            @Parameter(description = "Número da residência", required = true)
            @RequestParam("address_number") String addressNumber,

            @Parameter(description = "Complemento do endereço (ex: apto 402)")
            @RequestParam("address_detail") String addressDetail,

            @Parameter(
                    description = """
                        CEP do endereço (obrigatório). 
                        Utilize apenas um dos CEPs já cadastrados no banco:

                        • 44001002
                        • 44002110
                        • 44004224
                        • 44006146
                        • 44009014
                        • 44010160
                        • 44013214
                        • 44015240
                        • 44020224
                        • 44032900
                        """,
                    required = true
            )
            @RequestParam("zip_code") String zipCode,

            @Parameter(
                    description = """
                Habilidades do usuário separadas por vírgula.
                Informe os IDs conforme a lista abaixo:

                1 - Organização e Planejamento de Eventos
                2 - Comunicação e Relações Públicas
                3 - Domínio de Redes Sociais e Marketing Digital
                4 - Conhecimento em Design Gráfico (Ex: Canva, Photoshop)
                5 - Habilidades de Ensino ou Treinamento
                6 - Trabalho Manual (Ex: Construção, Jardinagem, Artesanato)
                7 - Capacidade de Coleta e Análise de Dados
                8 - Suporte Administrativo ou Escritório
                9 - Primeiros Socorros ou Suporte de Saúde
                10 - Condução de Veículos e Logística
                """,
                    required = true
            )
            @RequestParam String abilities
    ) {

        AppUser appuser = userService.registerUser(
                email,
                password,
                name,
                availabilityStartTime,
                availabilityEndTime,
                addressNumber,
                addressDetail,
                zipCode,
                abilities
        );

        return ResponseEntity.ok(appuser);
    }

    @Operation(
            summary = "Login do usuário",
            description = """
                Realiza autenticação e retorna um token JWT no campo `token`.

                Esse endpoint **não exige autenticação**.
                O Swagger irá capturar automaticamente a resposta e armazenar o token
                para uso nos demais endpoints protegidos.
                """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "{\"token\": \"jwt-token-aqui\"}")
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Parameter(description = "E-mail do usuário", required = true)
            @RequestParam String email,

            @Parameter(description = "Senha do usuário", required = true)
            @RequestParam String password
    ) {

        Optional<AppUser> user = userService.findByEmail(email);

        if (user.isEmpty() || !encoder.matches(password, user.get().getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciais inválidas"));
        }

        // 🔥 JWT retornado no formato esperado pelo script do Swagger:
        String token = JwtUtil.generateToken(user.get().getEmail());

        return ResponseEntity.ok(Map.of("token", token));
    }
}
