package com.conectacausa.webservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserAbilityId implements Serializable {
    private static final long serialVersionUID = 3683825487936763209L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "ability_id", nullable = false)
    private Integer abilityId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        UserAbilityId entity = (UserAbilityId) o;
        return Objects.equals(this.abilityId, entity.abilityId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abilityId, userId);
    }

}