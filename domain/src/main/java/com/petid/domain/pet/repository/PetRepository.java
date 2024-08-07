package com.petid.domain.pet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.petid.domain.pet.model.Pet;

@Repository
public interface PetRepository {
    Pet createPet(Pet pet);
    Pet updatePet(Pet pet);
    void deletePet(Long petId);
    Optional<Pet> findPetById(Long petId);
    List<Pet> findAllPets();

}
