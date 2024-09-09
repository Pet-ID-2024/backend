package com.petid.domain.pet.model;

import java.util.List;

import com.petid.domain.member.model.Member;

public record Pet(
		Long petId,
		Integer ownerId,
		String petRegNo,
		String petName,
		String petBirthDate,
		Character petSex,
		Character petNeuteredYn,
		String petNeuteredDate,
		String petAddr,
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
        		appearance,
        		petImages
        );
    }
}


