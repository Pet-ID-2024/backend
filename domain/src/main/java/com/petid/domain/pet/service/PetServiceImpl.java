package com.petid.domain.pet.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.pet.model.PetImage;
import com.petid.domain.pet.repository.PetAppearanceRepository;
import com.petid.domain.pet.repository.PetImageRepository;
import com.petid.domain.pet.repository.PetRepository;


import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
  
  private final PetRepository petRepo;
  private final PetAppearanceRepository petAppearanceRepo;
  private final PetImageRepository PetImgRepo;

  @Override
  @Transactional
  public Pet createPet(Pet pet) {
      return petRepo.createPet(pet);
  }

  @Override
  @Transactional
  public Pet updatePet(Pet pet) {
      return petRepo.updatePet(pet);
  }

  @Override
  @Transactional
  public void deletePet(Long petId) {
      petRepo.deletePet(petId);
  }

  @Override
  public Optional<Pet> findPetById(Long petId) {
      return petRepo.findPetById(petId);
  }

  @Override
  public List<Pet> findAllPets() {
      return petRepo.findAllPets();
  }

  @Override
  @Transactional
  public PetImage createPetImage(Long petId, PetImage petImage) {
    if (!petRepo.findPetById(petId).isPresent()) {
      throw new RuntimeException("Pet not found for ID: " + petId);
    }
    return PetImgRepo.createPetImage(petImage);
  }

  @Override
  @Transactional
  public PetImage updatePetImage(Long petId, PetImage petImage) {
    if (!petRepo.findPetById(petId).isPresent()) {
      throw new RuntimeException("Pet not found for ID: " + petId);
    }
    
    return PetImgRepo.updatePetImage(petImage);
  }

  @Override
  @Transactional
  public void deletePetImage(Long petImageId) {
      PetImgRepo.deletePetImage(petImageId);
  }

  @Override
  public Optional<PetImage> findPetImageById(Long petId, Long petImageId) {
    if (!petRepo.findPetById(petId).isPresent()) {
      throw new RuntimeException("Pet not found for ID: " + petId);
    }
      return PetImgRepo.findPetImageById(petImageId);
  }

  @Override
  public List<PetImage> findAllPetImages() {
      return PetImgRepo.findAllPetImages();
  }

  @Override
  @Transactional
  public PetAppearance createPetAppearance(Long petId, PetAppearance petAppearance) {
    if (!petRepo.findPetById(petId).isPresent()) {
      throw new RuntimeException("Pet not found for ID: " + petId);
    }
    
    return petAppearanceRepo.createPetAppearance(petAppearance);
  }

  @Override
  @Transactional
  public PetAppearance updatePetAppearance(Long petId, PetAppearance petAppearance) {
    if (!petRepo.findPetById(petId).isPresent()) {
      throw new RuntimeException("Pet not found for ID: " + petId);
    }
    
    return petAppearanceRepo.updatePetAppearance(petAppearance);
  }

  @Override
  @Transactional
  public void deletePetAppearance(Long appearanceId) {
    petAppearanceRepo.deletePetAppearance(appearanceId);
  }

  @Override
  public Optional<PetAppearance> findPetAppearanceById(Long appearanceId) {
      return petAppearanceRepo.findPetAppearanceById(appearanceId);
  }

  @Override
  public List<PetAppearance> findAllPetAppearances() {
      return petAppearanceRepo.findAllPetAppearances();
  }
  
    
}
