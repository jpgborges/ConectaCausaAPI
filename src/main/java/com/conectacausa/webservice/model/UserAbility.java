package com.conectacausa.webservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_ability", schema = "conectacausa")
public class UserAbility {
    @SequenceGenerator(name = "user_ability_id_gen", sequenceName = "seq_user", allocationSize = 1)
    @EmbeddedId
    private UserAbilityId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @MapsId("abilityId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ability_id", nullable = false)
    private Ability ability;

    public UserAbilityId getId() {
        return id;
    }

    public void setId(UserAbilityId id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

}