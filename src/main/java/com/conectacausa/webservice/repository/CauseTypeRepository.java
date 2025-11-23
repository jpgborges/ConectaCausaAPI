package com.conectacausa.webservice.repository;

import com.conectacausa.webservice.model.CauseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CauseTypeRepository extends JpaRepository<CauseType, Integer> {

    Optional<CauseType> findByid(Integer id);
}

