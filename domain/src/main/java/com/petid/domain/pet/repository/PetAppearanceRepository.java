package com.petid.domain.pet.repository;

import java.util.List;
import java.util.Optional;

import com.petid.domain.pet.model.PetAppearance;

public interface PetAppearanceRepository {
    
    PetAppearance createPetAppearance(Long petId, PetAppearance petAppearance);
    PetAppearance updatePetAppearance(PetAppearance petAppearance);
    void deletePetAppearanceByPetId(Long petId);
    Optional<PetAppearance> findPetAppearanceById(Long appearanceId);
    List<PetAppearance> findAllPetAppearances();
}
