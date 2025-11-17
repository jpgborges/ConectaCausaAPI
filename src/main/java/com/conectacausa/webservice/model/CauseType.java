package com.conectacausa.webservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cause_type", schema = "conectacausa")
public class CauseType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cause_type_id_gen")
    @SequenceGenerator(name = "cause_type_id_gen", sequenceName = "seq_cause_type", allocationSize = 1)
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