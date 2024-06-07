package com.petid.domain.pet.model;

public record PetImage(
		Long id,
		Pet pet,
		String imagePath
) {
}

