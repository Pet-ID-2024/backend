package com.petid.domain.pet.repository;

import com.petid.domain.pet.entity.PetAppearance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetAppearanceJpaRepository extends JpaRepository<PetAppearance, Long> {
    Optional<PetAppearance> findByPetAppearanceId(Long id);
}
