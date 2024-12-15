package com.petid.api.pet.dto;

import com.petid.domain.pet.model.Pet;
import com.petid.domain.type.Chip;

import java.util.List;

public record PetDto() {
    public record Response(
            Long petId,
            Long ownerId,
            String petRegNo,
            String petName,
            String petBirthDate,
            Character petSex,
            Character petNeuteredYn,
            String petNeuteredDate,
            Chip chipType,
            PetAppearanceDto.Response appearance,
            List<PetImageDto.Response> petImages,
            String signPath
    ) {
        public static Response from(Pet pet) {
            return new Response(
                    pet.petId(),
                    pet.ownerId(),
                    pet.petRegNo(),
                    pet.petName(),
                    pet.petBirthDate(),
                    pet.petSex(),
                    pet.petNeuteredYn(),
                    pet.petNeuteredDate(),
                    pet.chipType(),
                    PetAppearanceDto.Response.from(pet.appearance()),
                    pet.petImages().stream().map(PetImageDto.Response::from).toList(),
                    pet.signPath()
            );
        }
    }
}
