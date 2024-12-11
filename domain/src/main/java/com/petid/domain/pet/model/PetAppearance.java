package com.petid.domain.pet.model;

import com.petid.domain.type.Breed;

public record PetAppearance(
		Long appearanceId,		
		Breed breed,
		String hairColor,
		Integer weight,
		String hairLength
) {
	public PetAppearance update(
			PetAppearance updateData
	) {
		return new PetAppearance(
				appearanceId,
				breed,
				hairColor,
				updateData.weight(),
				hairLength
		);
	}
}





