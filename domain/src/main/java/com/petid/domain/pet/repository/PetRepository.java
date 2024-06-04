package com.petid.domain.pet.repository;

import com.petid.domain.pet.entity.Pet;

import java.util.Optional;

public interface PetRepository {
    Optional<Pet> findById(Long petId);
    Pet save(Pet pet) ;
    Iterable<Pet> findAll();
    void deleteById(Long id);
}
