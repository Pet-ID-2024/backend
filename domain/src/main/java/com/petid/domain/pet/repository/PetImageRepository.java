package com.petid.domain.pet.repository;

import com.petid.domain.pet.model.PetImage;

public interface PetImageRepository  {    
	PetImage save(Long petId,  PetImage petImage) ;
}
