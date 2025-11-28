package com.conectacausa.webservice.controller;

import com.conectacausa.webservice.dto.OpportunityDTO;
import com.conectacausa.webservice.dto.OrganizationDTO;
import com.conectacausa.webservice.mapper.OpportunityMapper;
import com.conectacausa.webservice.model.Opportunity;
import com.conectacausa.webservice.service.OpportunityService;
import com.conectacausa.webservice.view.OpportunityExtended;
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

    @PostMapping
    public ResponseEntity<?> registerOpportunity(
            @RequestParam String description,
            @RequestParam String hour,
            @RequestParam Integer duration,
            @RequestParam("organization_id") Integer organizationId,
            @RequestParam("cause_type_id") Integer causeTypeId,
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

    @GetMapping
    public ResponseEntity<?> getAllOpportunities() {

        List<OpportunityExtended> list = opportunityService.findAll();

        List<OpportunityDTO> dtoList = list.stream()
                .map(OpportunityMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtoList);
    }

}