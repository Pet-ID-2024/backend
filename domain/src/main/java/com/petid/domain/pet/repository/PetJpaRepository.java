package com.petid.domain.pet.repository;

import com.petid.domain.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetJpaRepository extends JpaRepository<Pet, Long> {
    @Query("SELECT pi FROM Pet pi WHERE pi.id = ?1")
    Optional<Pet> findbyId(Long petId);
    
}
