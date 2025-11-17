package com.conectacausa.webservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"user\"", schema = "conectacausa")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
    @SequenceGenerator(name = "user_id_gen", sequenceName = "seq_user", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "password", nullable = false, length = Integer.MAX_VALUE)
    private String password;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "availability_start_time", nullable = false)
    private String availabilityStartTime;

    @Column(name = "availability_end_time", nullable = false)
    private String availabilityEndTime;

    @Column(name = "address_number", nullable = false, length = Integer.MAX_VALUE)
    private String addressNumber;

    @Column(name = "address_detail", length = Integer.MAX_VALUE)
    private String addressDetail;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zip_code", nullable = false)
    private ZipCode zipCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailabilityStartTime() {
        return availabilityStartTime;
    }

    public void setAvailabilityStartTime(String availabilityStartTime) {
        this.availabilityStartTime = availabilityStartTime;
    }

    public String getAvailabilityEndTime() {
        return availabilityEndTime;
    }

    public void setAvailabilityEndTime(String availabilityEndTime) {
        this.availabilityEndTime = availabilityEndTime;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCode zipCode) {
        this.zipCode = zipCode;
    }

}