package com.conectacausa.webservice.view;

import com.conectacausa.webservice.model.AppUser;

import java.util.List;

public class AppUserExtended {
    private AppUser user;
    private List<String> abilities;

    public AppUserExtended(AppUser user, List<String> abilities) {
        this.user = user;
        this.abilities = abilities;
    }

    public AppUser getUser() {
        return user;
    }

    public List<String> getAbilities() {
        return abilities;
    }
}
