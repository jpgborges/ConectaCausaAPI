package com.conectacausa.webservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OpportunityAbilityId implements Serializable {
    private static final long serialVersionUID = -8146539290364419285L;
    @Column(name = "opportunity_id", nullable = false)
    private Integer opportunityId;

    @Column(name = "ability_id", nullable = false)
    private Integer abilityId;

    public Integer getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Integer opportunityId) {
        this.opportunityId = opportunityId;
    }

    public Integer getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(Integer abilityId) {
        this.abilityId = abilityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OpportunityAbilityId entity = (OpportunityAbilityId) o;
        return Objects.equals(this.opportunityId, entity.opportunityId) &&
                Objects.equals(this.abilityId, entity.abilityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opportunityId, abilityId);
    }

}