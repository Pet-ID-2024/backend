package com.petid.domain.pet.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.petid.domain.exception.PetAppearanceNotFoundException;
import com.petid.domain.exception.PetNotFoundException;
import com.petid.domain.pet.dto.PetAppearanceDto;
import com.petid.domain.pet.dto.PetDto;
import com.petid.domain.pet.dto.PetImageDto;
import com.petid.domain.pet.entity.Pet;
import com.petid.domain.pet.entity.PetAppearance;
import com.petid.domain.pet.entity.PetImage;
import com.petid.domain.pet.repository.PetAppearanceJpaRepository;
import com.petid.domain.pet.repository.PetImageJpaRepository;
import com.petid.domain.pet.repository.PetJpaRepository;
import com.petid.domain.type.Breed;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
  
  private PetJpaRepository petRepo;
  private PetAppearanceJpaRepository petAppearanceRepo;
  private PetImageJpaRepository PetImgRepo;

  @Override
  public Pet createPet(Pet pet) {

    if (pet.getPetName() == null || pet.getPetName().isEmpty()) {
        throw new IllegalArgumentException("Pet name cannot be empty");
    }
    
    return petRepo.save(pet);
  }

  @Override
  public List<Pet> getAllPets() {
    return petRepo.findAll();
  }

  @Override
  public Optional<Pet> getPetById(Long id) {
    return petRepo.findById(id);
  }

  @Override
  public Pet updatePet(PetDto petDto)  throws PetNotFoundException {
    Long petId = petDto.getId();
    Pet existingPet = petRepo.findById(petId)
        .orElseThrow(() -> new PetNotFoundException(petId));

    // Update pet data (assuming mapping logic exists)
    existingPet.setPetName(petDto.getPetName()); // Update other fields as needed
    petRepo.save(existingPet);

    return existingPet;
  }
    public PetAppearance updatePetAppearance(Long petId, Long PetAppearanceId, PetAppearanceDto appearanceDto) 
      throws PetNotFoundException, PetAppearanceNotFoundException {
    Pet existingPet = petRepo.findById(petId)
        .orElseThrow(() -> new PetNotFoundException(petId));

    PetAppearance existingAppearance = petAppearanceRepo.findByPetAppearanceId(PetAppearanceId)
        .orElseThrow(() -> new PetAppearanceNotFoundException(petId));

    // Update appearance data (assuming mapping logic exists)
    existingAppearance.setBreed(appearanceDto.getBreed()); // Update other fields as needed
    petAppearanceRepo.save(existingAppearance);

    return existingAppearance;
  }
  

    @Override
    public void deletePet(Long id) {
        petRepo.deleteById(id);
    }

    @Override
    public PetImage createImage(Long petId, PetImageDto imageDto) {
        PetImage petImageEntity = new PetImage();
       return PetImgRepo.save(null);
    }


  
    
}
