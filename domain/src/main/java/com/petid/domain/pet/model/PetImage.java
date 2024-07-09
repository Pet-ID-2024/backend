package com.petid.domain.pet.model;

public record PetImage(
		Long petImageId,
		Long petId,
		String imagePath
) {
}

