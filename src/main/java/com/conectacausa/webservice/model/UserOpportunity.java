package com.conectacausa.webservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_opportunity", schema = "conectacausa")
public class UserOpportunity {
    @SequenceGenerator(name = "user_opportunity_id_gen", sequenceName = "seq_user", allocationSize = 1)
    @EmbeddedId
    private UserOpportunityId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @MapsId("opportunityId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "opportunity_id", nullable = false)
    private Opportunity opportunity;

    public UserOpportunityId getId() {
        return id;
    }

    public void setId(UserOpportunityId id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

}