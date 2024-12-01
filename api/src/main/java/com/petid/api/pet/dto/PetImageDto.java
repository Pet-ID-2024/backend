package com.petid.api.pet.dto;

import com.petid.domain.pet.model.PetImage;

public record PetImageDto() {
    public record Request(
            long petImageId,
            long petId,
            String imagePath
    ) {
        public PetImage toDomain() {
            return new PetImage(
                    petImageId,
                    petId,
                    imagePath
            );
        }
    }

    public record Response(
            Long petImageId,
            Long petId,
            String imagePath
    ) {
        public static Response from(PetImage petImage) {
            return new Response(
                    petImage.petImageId(),
                    petImage.petId(),
                    petImage.imagePath()
            );
        }
    }
}
