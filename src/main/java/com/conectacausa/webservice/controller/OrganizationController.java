package com.conectacausa.webservice.controller;

import com.conectacausa.webservice.dto.OrganizationDTO;
import com.conectacausa.webservice.mapper.OrganizationMapper;
import com.conectacausa.webservice.model.Organization;
import com.conectacausa.webservice.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @Operation(
            summary = "Registrar nova organização",
            description = "Cria uma nova organização com seus dados básicos e CEP associado.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Organização registrada com sucesso",
                            content = @Content(schema = @Schema(implementation = OrganizationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados inválidos enviados pelo cliente"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<?> registerOrganization(
            @Parameter(description = "Nome da organização", required = true)
            @RequestParam String name,

            @Parameter(description = "E-mail para contato da organização", required = true)
            @RequestParam String email,

            @Parameter(description = "Número do endereço da organização", required = true)
            @RequestParam("address_number") String addressNumber,

            @Parameter(description = "Complemento do endereço da organização", required = true)
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
            @RequestParam("zip_code") String zipCode
    ) {

        Organization organization = organizationService.registerOrganization(
                name,
                email,
                addressNumber,
                addressDetail,
                zipCode
        );

        return ResponseEntity.ok(OrganizationMapper.toDTO(organization));
    }

    @Operation(
            summary = "Buscar organização por ID",
            description = "Retorna os dados de uma organização específica pelo seu identificador.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Organização encontrada",
                            content = @Content(schema = @Schema(implementation = OrganizationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Organização não encontrada"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<?> getOrganizationById(
            @Parameter(description = "ID da organização a ser consultada", required = true)
            @RequestParam Integer id
    ) {
        OrganizationDTO dto = organizationService.getOrganizationById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Atualizar organização",
            description = "Atualiza os dados básicos de uma organização existente.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Organização atualizada com sucesso",
                            content = @Content(schema = @Schema(implementation = OrganizationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Organização não encontrada"
                    )
            }
    )
    @PutMapping
    public ResponseEntity<?> updateOrganization(
            @Parameter(description = "ID da organização a ser atualizada", required = true)
            @RequestParam Integer id,

            @Parameter(description = "Nome atualizado da organização", required = true)
            @RequestParam String name,

            @Parameter(description = "E-mail atualizado da organização", required = true)
            @RequestParam String email,

            @Parameter(description = "Número atualizado do endereço", required = true)
            @RequestParam("address_number") String addressNumber,

            @Parameter(description = "Complemento atualizado do endereço", required = true)
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
            @RequestParam("zip_code") String zipCode
    ) {

        Organization updated = organizationService.updateOrganization(
                id, name, email, addressNumber, addressDetail, zipCode
        );

        return ResponseEntity.ok(OrganizationMapper.toDTO(updated));
    }

}
