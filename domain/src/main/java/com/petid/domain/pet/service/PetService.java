package com.petid.domain.pet.service;

import java.util.List;
import java.util.Optional;

import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.pet.model.PetImage;

public interface PetService {

    Pet createPet(Pet pet);
  
    List<Pet> getAllPets();
  
    Optional<Pet> getPetById(Long id);
  
    Pet updatePet(Pet pet);
  
    void deletePet(Long id);

    PetAppearance updatePetAppearance(Long petId, Long PetAppearanceId, PetAppearance petAppearance);

    PetImage createImage(Long petId, PetImage petImage);
  }
  