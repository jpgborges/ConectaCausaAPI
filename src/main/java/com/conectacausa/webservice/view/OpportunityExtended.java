package com.conectacausa.webservice.view;

import com.conectacausa.webservice.model.Opportunity;

import java.util.List;

public class OpportunityExtended {
    private Opportunity opportunity;
    private List<String> abilities;

    public OpportunityExtended(Opportunity opportunity, List<String> abilities) {
        this.opportunity = opportunity;
        this.abilities = abilities;
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }

    public List<String> getAbilities() {
        return abilities;
    }
}

