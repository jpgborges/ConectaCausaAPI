package com.conectacausa.webservice.repository;

import com.conectacausa.webservice.model.AppUser;
import com.conectacausa.webservice.model.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZipCodeRepository extends JpaRepository<ZipCode, String> {

    public  Optional<ZipCode> findByZipCode(String zipCode);
}

