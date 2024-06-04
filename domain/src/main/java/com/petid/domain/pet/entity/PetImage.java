package com.petid.domain.pet.entity;

public record PetImage(
		Long id,
		Pet pet,
		String imagePath
) {
}

