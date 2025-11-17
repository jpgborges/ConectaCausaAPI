package com.conectacausa.webservice.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "opportunity", schema = "conectacausa")
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opportunity_id_gen")
    @SequenceGenerator(name = "opportunity_id_gen", sequenceName = "seq_opportunity", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "cep", nullable = false, length = Integer.MAX_VALUE)
    private String cep;

    @Column(name = "hour", nullable = false)
    private LocalDate hour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cause_type_id")
    private CauseType causeType;

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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public LocalDate getHour() {
        return hour;
    }

    public void setHour(LocalDate hour) {
        this.hour = hour;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public CauseType getCauseType() {
        return causeType;
    }

    public void setCauseType(CauseType causeType) {
        this.causeType = causeType;
    }

}