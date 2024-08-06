package com.petid.domain.pet.repository;

import java.util.List;
import java.util.Optional;

import com.petid.domain.pet.model.PetAppearance;

public interface PetAppearanceRepository {
    
    PetAppearance createPetAppearance(PetAppearance petAppearance);
    PetAppearance updatePetAppearance(PetAppearance petAppearance);
    void deletePetAppearanceById(Long appearanceId);
    Optional<PetAppearance> findPetAppearanceById(Long appearanceId);
    List<PetAppearance> findAllPetAppearances();
}
