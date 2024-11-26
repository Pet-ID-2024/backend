package com.petid.domain.pet.repository;

import com.petid.domain.pet.model.Pet;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository {
    Pet createPet(Pet pet);
    Pet updatePet(Pet pet);
    void deletePet(Long petId);
    Optional<Pet> findPetById(Long petId);
    List<Pet> findAllPets();

    Pet save(Pet pet);

    Optional<Pet> findPetByOwnerId(long ownerId);
}
