package com.petid.domain.pet.entity;

import com.petid.domain.type.Breed;

public record PetAppearance(
		Long id,
		Pet pet,
		Breed breed,
		String hairColor,
		Integer weight,
		String hairLength
) {
}





