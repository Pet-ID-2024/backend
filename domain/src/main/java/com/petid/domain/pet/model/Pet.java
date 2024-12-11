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
		String petAddrDetails,
		Chip chipType,
		PetAppearance appearance,
		List<PetImage> petImages,
		String signPath
) {
	public Pet setOwnerId(
			long ownerId
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
				petAddrDetails,
				chipType,
				appearance,
				petImages,
				signPath
		);
	}

	public Pet updatePetAppearance(
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
				petAddrDetails,
				chipType,
        		appearance,
        		petImages,
				signPath
        );
    }
	
	public Pet updatePetImages(
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
				petAddrDetails,
				chipType,
        		appearance,
        		petImages,
				signPath
        );
    }

	public Pet update(
			Pet updatePetData
	) {
		return new Pet(
				petId,
				ownerId,
				petRegNo,
				petName,
				petBirthDate,
				petSex,
				petNeuteredYn,
				updatePetData.petNeuteredDate(),
				petAddr,
				petAddrDetails,
				chipType,
				appearance.update(updatePetData.appearance()),
				petImages,
				signPath
		);
	}
}


