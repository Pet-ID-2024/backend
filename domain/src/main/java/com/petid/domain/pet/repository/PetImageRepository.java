package com.petid.domain.pet.repository;

import com.petid.domain.pet.entity.PetImage;

public interface PetImageRepository  {    
	PetImage save(Long petId,  PetImage petImage) ;
}
