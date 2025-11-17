package com.conectacausa.webservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ability", schema = "conectacausa")
public class Ability {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ability_id_gen")
    @SequenceGenerator(name = "ability_id_gen", sequenceName = "seq_ability", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}