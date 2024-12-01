package com.petid.domain.pet.service;

import com.petid.domain.member.model.MemberAuthInfo;
import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.pet.model.PetImage;

import java.util.List;
import java.util.Optional;

public interface PetService {

  Pet createPet(Pet pet, MemberAuthInfo memberAuth);
  Pet updatePet(long petId, Pet updatePetData);
  void deletePet(Long petId);
  Pet findPetById(Long petId);
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
  
  void deletePetById(Long petId);
  void deletePetImage(long petId, long imageId);
}
  