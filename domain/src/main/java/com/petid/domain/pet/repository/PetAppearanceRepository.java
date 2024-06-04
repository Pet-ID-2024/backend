package com.petid.domain.pet.repository;

import com.petid.domain.pet.entity.Pet;
import com.petid.domain.pet.entity.PetAppearance;

import java.util.Optional;

public interface PetAppearanceRepository {
    Optional<PetAppearance> findByPetAppearanceId(Long id);
    PetAppearance save(PetAppearance pet) ;
}
