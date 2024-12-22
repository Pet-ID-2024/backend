package com.petid.domain.pet.model;

public record PetAppearance(
		Long appearanceId,		
		String breed,
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
				(updateData.weight() != null) ? updateData.weight() : weight,
				hairLength
		);
	}
}





