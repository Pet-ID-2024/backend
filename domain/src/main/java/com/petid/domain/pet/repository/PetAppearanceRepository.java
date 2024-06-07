package com.petid.domain.pet.repository;

import java.util.Optional;

import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.model.PetAppearance;

public interface PetAppearanceRepository {
    Optional<PetAppearance> findByPetAppearanceId(Long id);
    PetAppearance save(PetAppearance pet) ;
}
