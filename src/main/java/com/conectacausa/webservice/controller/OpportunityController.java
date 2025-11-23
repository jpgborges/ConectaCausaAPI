package com.conectacausa.webservice.controller;

import com.conectacausa.webservice.mapper.OpportunityMapper;
import com.conectacausa.webservice.model.Opportunity;
import com.conectacausa.webservice.service.OpportunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam("cause_type_id") Integer causeTypeId
    ) {

        Opportunity opportunity = opportunityService.registerOpportunity(
                description,
                hour,
                duration,
                organizationId,
                causeTypeId
        );

        return ResponseEntity.ok(OpportunityMapper.toDTO(opportunity));
    }
}