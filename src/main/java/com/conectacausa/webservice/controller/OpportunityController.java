package com.conectacausa.webservice.controller;

import com.conectacausa.webservice.dto.OpportunityDTO;
import com.conectacausa.webservice.mapper.OpportunityMapper;
import com.conectacausa.webservice.model.Opportunity;
import com.conectacausa.webservice.service.OpportunityService;
import com.conectacausa.webservice.view.OpportunityExtended;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunityController {

    private final OpportunityService opportunityService;

    public OpportunityController(OpportunityService opportunityService){
        this.opportunityService = opportunityService;
    }

    @Operation(
            summary = "Registrar nova oportunidade",
            description = "Cria uma nova oportunidade vinculada a uma organização e um tipo de causa.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Oportunidade registrada com sucesso",
                            content = @Content(schema = @Schema(implementation = OpportunityDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados inválidos enviados pelo cliente"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<?> registerOpportunity(
            @Parameter(description = "Descrição detalhada da oportunidade", required = true)
            @RequestParam String description,

            @Parameter(description = "Horário em que a atividade ocorrerá (ex: 14:00)", required = true)
            @RequestParam String hour,

            @Parameter(description = "Duração em horas da atividade", required = true)
            @RequestParam Integer duration,

            @Parameter(description = "ID da organização responsável", required = true)
            @RequestParam("organization_id") Integer organizationId,

            @Parameter(
                    description = """
                ID do tipo de causa associado à oportunidade.
                Informe um dos valores conforme a lista abaixo:
        
                1 - Combate à Fome
                2 - Preservação Ambiental
                3 - Incentivo à Educação e Cultura
                4 - Promoção da Saúde
                5 - Defesa dos Direitos Humanos
                6 - Proteção Animal
                7 - Desenvolvimento Comunitário
                8 - Apoio a Crianças e Adolescentes
                9 - Combate ao Racismo e Desigualdade
                10 - Assistência a Pessoas em Situação de Vulnerabilidade
        
                Exemplo: 4
                """,
                    required = true
            )
            @RequestParam("cause_type_id") Integer causeTypeId,

            @Parameter(
                    description = """
                Lista de habilidades necessárias para participar, separadas por vírgula.
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

                Exemplo: "1,4,10"
                """,
                    required = true
            )
            @RequestParam String abilities
    ) {

        OpportunityExtended opportunity = opportunityService.registerOpportunity(
                description,
                hour,
                duration,
                organizationId,
                causeTypeId,
                abilities
        );

        return ResponseEntity.ok(OpportunityMapper.toDTO(opportunity));
    }

    @Operation(
            summary = "Listar todas as oportunidades",
            description = "Retorna todas as oportunidades cadastradas no sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de oportunidades retornada com sucesso",
                            content = @Content(schema = @Schema(implementation = OpportunityDTO.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<?> getAllOpportunities() {

        List<OpportunityExtended> list = opportunityService.findAll();

        List<OpportunityDTO> dtoList = list.stream()
                .map(OpportunityMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtoList);
    }

}
