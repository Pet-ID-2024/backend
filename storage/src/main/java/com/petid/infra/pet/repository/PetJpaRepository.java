package com.petid.infra.pet.repository;

import com.petid.infra.pet.entity.PetEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PetJpaRepository extends JpaRepository<PetEntity, Long> {    
}
