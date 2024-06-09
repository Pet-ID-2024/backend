package com.petid.domain.pet.repository;

import java.util.List;
import java.util.Optional;

import com.petid.domain.pet.model.PetImage;

public interface PetImageRepository  {    
	 PetImage createPetImage(PetImage petImage);
    PetImage updatePetImage(PetImage petImage);
    void deletePetImage(Long petImageId);
    Optional<PetImage> findPetImageById(Long petImageId);
    List<PetImage> findAllPetImages();

}
