package com.petid.domain.pet.model;

import com.petid.domain.type.Chip;

import java.util.List;

public record Pet(
		Long petId,
		Long ownerId,
		String petRegNo,
		String petName,
		String petBirthDate,
		Character petSex,
		Character petNeuteredYn,
		String petNeuteredDate,
		String petAddr,
		Chip chipType,
		PetAppearance appearance,
		List<PetImage> petImages
) {
	public Pet updatePetAPpearance(
			PetAppearance appearance
    ) {
        return new Pet(
        		petId,
        		ownerId,
        		petRegNo,
        		petName,
        		petBirthDate,
        		petSex,
        		petNeuteredYn,
        		petNeuteredDate,
        		petAddr,
				chipType,
        		appearance,
        		petImages
        );
    }
	
	public Pet updatePetimages(
			List<PetImage> petImages
    ) {
        return new Pet(
        		petId,
        		ownerId,
        		petRegNo,
        		petName,
        		petBirthDate,
        		petSex,
        		petNeuteredYn,
        		petNeuteredDate,
        		petAddr,
				chipType,
        		appearance,
        		petImages
        );
    }
}


