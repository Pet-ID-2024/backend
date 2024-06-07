package com.petid.domain.pet.repository;

import java.util.Optional;

import com.petid.domain.pet.model.Pet;

public interface PetRepository {
    Optional<Pet> findById(Long petId);
    Pet save(Pet pet) ;
    Iterable<Pet> findAll();
    void deleteById(Long id);
}
