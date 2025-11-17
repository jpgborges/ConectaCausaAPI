package com.conectacausa.webservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserOpportunityId implements Serializable {
    private static final long serialVersionUID = -8459050016492619470L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "opportunity_id", nullable = false)
    private Integer opportunityId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Integer opportunityId) {
        this.opportunityId = opportunityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserOpportunityId entity = (UserOpportunityId) o;
        return Objects.equals(this.opportunityId, entity.opportunityId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opportunityId, userId);
    }

}