package com.petid.domain.pet.repository;

import com.petid.domain.pet.entity.Pet;
import com.petid.domain.pet.entity.PetImage;

import java.util.Optional;

public interface PetImageRepository  {    
	PetImage save(Long petId,  PetImage petImage) ;
}
