package com.petid.api.pet.dto;

import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.pet.model.PetImage;
import com.petid.domain.type.Chip;

import java.util.List;

public record PetIdDto() {
    public record Request(
            Long petId,
            Long ownerId,
            String petName,
            String petBirthDate,
            Character petSex,
            Character petNeuteredYn,
            String petNeuteredDate,
            String petAddr,
            Chip chipType,
            PetAppearance appearance,
            List<PetImage> petImages,
            PetIdProposerDto proposer,
            String sign
    ) {
        public Pet toPetDomain() {
            return new Pet(
                    petId,
                    null,
                    null,
                    petName,
                    petBirthDate,
                    petSex,
                    petNeuteredYn,
                    petNeuteredDate,
                    petAddr,
                    chipType,
                    appearance,
                    petImages,
                    sign
            );
        }
    }
}
