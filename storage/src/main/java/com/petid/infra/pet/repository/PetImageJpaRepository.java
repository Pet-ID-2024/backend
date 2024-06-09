package com.petid.infra.pet.repository;


import com.petid.infra.pet.entity.PetImageEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PetImageJpaRepository extends JpaRepository<PetImageEntity, Long> {
    
}
