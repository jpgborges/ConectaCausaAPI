package com.conectacausa.webservice.controller;

import com.conectacausa.webservice.dto.OrganizationDTO;
import com.conectacausa.webservice.mapper.OrganizationMapper;
import com.conectacausa.webservice.model.Organization;
import com.conectacausa.webservice.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<?> registerOrganization(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam("address_number") String addressNumber,
            @RequestParam("address_detail") String addressDetail,
            @RequestParam("zip_code") String zipCode
    ) {

        Organization organization = organizationService.registerOrganization(
                name,
                email,
                addressNumber,
                addressDetail,
                zipCode
        );

        return ResponseEntity.ok(organization);
    }

    @GetMapping
    public ResponseEntity<?> getOrganizationById(@RequestParam Long id) {
        OrganizationDTO dto = organizationService.getOrganizationById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<?> updateOrganization(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam("address_number") String addressNumber,
            @RequestParam("address_detail") String addressDetail,
            @RequestParam("zip_code") String zipCode
    ) {

        Organization updated = organizationService.updateOrganization(
                id, name, email, addressNumber, addressDetail, zipCode
        );

        return ResponseEntity.ok(OrganizationMapper.toDTO(updated));
    }


}
