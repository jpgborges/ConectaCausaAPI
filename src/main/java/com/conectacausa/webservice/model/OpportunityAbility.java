package com.conectacausa.webservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "opportunity_ability", schema = "conectacausa")
public class OpportunityAbility {
    @SequenceGenerator(name = "opportunity_ability_id_gen", sequenceName = "seq_opportunity", allocationSize = 1)
    @EmbeddedId
    private OpportunityAbilityId id;

    @MapsId("opportunityId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "opportunity_id", nullable = false)
    private Opportunity opportunity;

    @MapsId("abilityId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ability_id", nullable = false)
    private Ability ability;

    public OpportunityAbilityId getId() {
        return id;
    }

    public void setId(OpportunityAbilityId id) {
        this.id = id;
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

}