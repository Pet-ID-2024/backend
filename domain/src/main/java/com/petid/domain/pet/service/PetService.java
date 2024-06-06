package com.petid.domain.pet.service;


import com.petid.domain.pet.dto.PetAppearanceDto;
import com.petid.domain.pet.dto.PetDto;
import com.petid.domain.pet.dto.PetImageDto;
import com.petid.domain.pet.entity.Pet;
import com.petid.domain.pet.entity.PetAppearance;
import com.petid.domain.pet.entity.PetImage;

import java.util.List;
import java.util.Optional;

public interface PetService {

    Pet createPet(Pet pet);
  
    List<Pet> getAllPets();
  
    Optional<Pet> getPetById(Long id);
  
    Pet updatePet(PetDto pet);
  
    void deletePet(Long id);

    PetAppearance updatePetAppearance(Long petId, Long PetAppearanceId, PetAppearanceDto appearanceDto);

    PetImage createImage(Long petId, PetImageDto imageDto);
  }
  