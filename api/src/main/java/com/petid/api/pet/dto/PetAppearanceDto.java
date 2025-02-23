package com.petid.api.pet.dto;

import com.petid.domain.pet.model.PetAppearance;

public record PetAppearanceDto() {
    public record Request(
            Long appearanceId,
            String breed,
            String hairColor,
            Double weight,
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
            String breed,
            String hairColor,
            Double weight,
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
