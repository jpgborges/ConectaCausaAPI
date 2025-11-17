package com.conectacausa.webservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "zip_code", schema = "conectacausa")
public class ZipCode {
    @Id
    @SequenceGenerator(name = "zip_code_id_gen", sequenceName = "seq_user", allocationSize = 1)
    @Column(name = "zip_code", nullable = false, length = Integer.MAX_VALUE)
    private String zipCode;

    @Column(name = "street", nullable = false, length = Integer.MAX_VALUE)
    private String street;

    @Column(name = "neighborhood", nullable = false, length = Integer.MAX_VALUE)
    private String neighborhood;

    @Column(name = "city", nullable = false, length = Integer.MAX_VALUE)
    private String city;

    @Column(name = "state_code", nullable = false, length = Integer.MAX_VALUE)
    private String stateCode;

    @Column(name = "country_code", nullable = false, length = Integer.MAX_VALUE)
    private String countryCode;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}