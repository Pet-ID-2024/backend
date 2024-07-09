package com.petid.domain.pet.model;

import java.time.LocalDateTime;
import java.util.List;

public record Pet(
		Long petId,
		String petRegNo,
		String petName,
		String petBirthDate,
		Character petSex,
		Character petNeuteredYn,
		LocalDateTime petNeuteredDate,
		String petAddr,
		PetAppearance appearance,
		List<PetImage> petImages
) {
}

