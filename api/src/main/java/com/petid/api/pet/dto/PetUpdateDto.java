package com.petid.api.pet.dto;

import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.model.PetAppearance;

public record PetUpdateDto() {
    public record Request(
            String petNeuteredDate,
            PetAppearanceUpdateDto appearance
    ) {
        public Pet toDomain() {
            return new Pet(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    petNeuteredDate,
                    null,
                    null,
                    null,
                    appearance.toDomain(),
                    null,
                    null
            );
        }
    }

    public record PetAppearanceUpdateDto(
            int weight
    ){
        public PetAppearance toDomain() {
            return new PetAppearance(
                    null,
                    null,
                    null,
                    weight,
                    null
            );
        }
    }
}
