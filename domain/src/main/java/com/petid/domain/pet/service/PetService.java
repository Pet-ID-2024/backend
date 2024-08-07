package com.petid.domain.pet.service;

import java.util.List;
import java.util.Optional;

import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.pet.model.PetImage;

public interface PetService {

  Pet createPet(Pet pet);
  Pet updatePet(Long petId, Pet pet);
  void deletePet(Long petId);
  Optional<Pet> findPetById(Long petId);
  List<Pet> findAllPets();

  PetImage createPetImage(Long petId, PetImage petImage);
  PetImage updatePetImage(Long petId, PetImage petImage);
  void deletePetImage(Long petImageId);
  Optional<PetImage> findPetImageById(Long petId, Long petImageId);
  List<PetImage> findAllPetImages();

  PetAppearance createPetAppearance(Long petId, PetAppearance petAppearance);
  PetAppearance updatePetAppearance(Long petId, PetAppearance petAppearance);
  void deletePetAppearance(Long appearanceId);
  Optional<PetAppearance> findPetAppearanceById(Long appearanceId);
  List<PetAppearance> findAllPetAppearances();
  
  public void deletePetById(Long petId);
  public void deletePetImage(Long petId, Long imageId);
 }
  