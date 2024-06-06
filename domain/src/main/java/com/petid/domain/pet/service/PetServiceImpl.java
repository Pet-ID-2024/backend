package com.petid.domain.pet.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.petid.domain.exception.PetAppearanceNotFoundException;
import com.petid.domain.exception.PetNotFoundException;

import com.petid.domain.pet.entity.Pet;
import com.petid.domain.pet.entity.PetAppearance;
import com.petid.domain.pet.entity.PetImage;
import com.petid.domain.pet.repository.PetAppearanceRepository;
import com.petid.domain.pet.repository.PetImageRepository;
import com.petid.domain.pet.repository.PetRepository;
import com.petid.domain.type.Breed;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
  
  private PetRepository petRepo;
  private PetAppearanceRepository petAppearanceRepo;
  private PetImageRepository PetImgRepo;

  @Override
  public Pet createPet(Pet pet) {

    if (pet.petName() == null || pet.petName().isEmpty()) {
        throw new IllegalArgumentException("Pet name cannot be empty");
    }
    
    return petRepo.save(pet);
  }

  @Override
  public List<Pet> getAllPets() {
    return (List<Pet>) petRepo.findAll();
  }

  @Override
  public Optional<Pet> getPetById(Long id) {
    return petRepo.findById(id);
  }

  @Override
  public Pet updatePet(Pet pet)  throws PetNotFoundException {
    Long petId = pet.id();
    Pet existingPet = petRepo.findById(petId)
        .orElseThrow(() -> new PetNotFoundException(petId));

    // Update pet data (assuming mapping logic exists)
    //existingPet.setPetName(petDto.getPetName()); // Update other fields as needed
    petRepo.save(existingPet);

    return existingPet;
  }
    public PetAppearance updatePetAppearance(Long petId, Long PetAppearanceId, PetAppearance appearance) 
      throws PetNotFoundException, PetAppearanceNotFoundException {
    Pet existingPet = petRepo.findById(petId)
        .orElseThrow(() -> new PetNotFoundException(petId));

    PetAppearance existingAppearance = petAppearanceRepo.findByPetAppearanceId(PetAppearanceId)
        .orElseThrow(() -> new PetAppearanceNotFoundException(petId));

    // Update appearance data (assuming mapping logic exists)
   // existingAppearance.setBreed(appearanceDto.getBreed()); // Update other fields as needed
    petAppearanceRepo.save(existingAppearance);

    return existingAppearance;
  }
  

    @Override
    public void deletePet(Long id) {
        petRepo.deleteById(id);
    }

    @Override
    public PetImage createImage(Long petId, PetImage petImage) {
        
       return PetImgRepo.save(petId, petImage);
    }


  
    
}
