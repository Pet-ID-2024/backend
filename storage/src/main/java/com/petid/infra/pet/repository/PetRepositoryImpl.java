package com.petid.infra.pet.repository;

import com.petid.domain.exception.PetNotFoundException;
import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.repository.PetRepository;
import com.petid.infra.pet.entity.PetEntity;

import jakarta.transaction.Transactional; 
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class PetRepositoryImpl implements PetRepository {

    private final PetJpaRepository petJpaRepo;
    
    @Override
    @Transactional
    public Pet createPet(Pet pet) {
        PetEntity petEntity = PetEntity.from(pet);
        PetEntity savedPetEntity = petJpaRepo.save(petEntity);
        return savedPetEntity.toDomain();
    }

    @Override
    @Transactional
    public Pet updatePet(Pet pet) {
        Optional<PetEntity> optionalPetEntity = petJpaRepo.findById(pet.petId());
        if (optionalPetEntity.isPresent()) {
            PetEntity petEntity = optionalPetEntity.get();
            PetEntity updatedPetEntity = PetEntity.from(pet);
            updatedPetEntity.setId(petEntity.getId());
            petJpaRepo.save(updatedPetEntity);
            return updatedPetEntity.toDomain();
        }
        throw new PetNotFoundException(pet.petId());
    }

    @Override
    @Transactional
    public void deletePet(Long id) {
        petJpaRepo.deleteById(id);
    }

    @Override
    public Optional<Pet> findPetById(Long petId) {
        return petJpaRepo.findById(petId).map(PetEntity::toDomain);
    }

    @Override
    public List<Pet> findAllPets() {
        return petJpaRepo.findAll().stream().map(PetEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Pet save(Pet pet) {
        PetEntity petEntity = PetEntity.from(pet);

        return petJpaRepo.save(petEntity).toDomain();
    }

    @Override
    public Optional<Pet> findPetByOwnerId(long ownerId) {
        return petJpaRepo.findByOwnerId(ownerId)
                .map(PetEntity::toDomain);
    }


}
