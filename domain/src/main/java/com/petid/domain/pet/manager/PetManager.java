package com.petid.domain.pet.manager;

import com.petid.domain.exception.PetNotFoundException;
import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PetManager {
    private final PetRepository petRepository;

    public Pet get(
            Long id
    ) {
        return petRepository.findPetById(id)
                .orElseThrow(() -> new PetNotFoundException(id));
    }

    public boolean isOwnerPetExists(
            Long ownerId
    ) {
        return petRepository.findPetByOwnerId(ownerId).isPresent();
    }

    public boolean existByPetId(long petId) {
        return petRepository.findPetById(petId).isPresent();
    }
}
