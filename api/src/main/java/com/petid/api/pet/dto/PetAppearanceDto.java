package com.petid.api.pet.dto;

import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.type.Breed;

public record PetAppearanceDto() {
    public record Request(
            Long appearanceId,
            Breed breed,
            String hairColor,
            Integer weight,
            String hairLength
    ) {
        public PetAppearance toDomain() {
            return new PetAppearance(
                    appearanceId,
                    breed,
                    hairColor,
                    weight,
                    hairLength
            );
        }
    }

    public record Response(
            Long appearanceId,
            Breed breed,
            String hairColor,
            Integer weight,
            String hairLength
    ) {
        public static Response from(PetAppearance petAppearance) {
            return new Response(
                    petAppearance.appearanceId(),
                    petAppearance.breed(),
                    petAppearance.hairColor(),
                    petAppearance.weight(),
                    petAppearance.hairLength()
            );
        }
    }
}
