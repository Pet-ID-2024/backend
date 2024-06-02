package com.petid.domain.pet.repository;

import com.petid.domain.pet.entity.PetImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetImageJpaRepository extends JpaRepository<PetImage, Long> {
    Optional<PetImage> findById(Long id);    
}
