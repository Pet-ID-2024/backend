package com.petid.infra.pet.repository;

import com.petid.domain.pet.entity.Pet;
import com.petid.domain.pet.repository.PetRepository;
import com.petid.infra.pet.entity.PetEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class PetRepositoryImpl implements PetRepository {

    private final PetJpaRepository petJpaRepo;
    
    @Override
    public Optional<Pet> findById(Long id) {  // Changed method name to findById (convention)
        return petJpaRepo.findById(id)
        		.map(PetEntity::toDomain);
    }

    public Pet save(Pet pet) {
        PetEntity petEntity = PetEntity.from(pet);
        PetEntity savedEntity = petJpaRepo.save(petEntity);
        return savedEntity.toDomain();  // Assuming toDomain() converts back to Pet
    }

    public Iterable<Pet> findAll() {
        Iterable<PetEntity> entities = petJpaRepo.findAll();
        return StreamSupport.stream(entities.spliterator(), false)
                .map(PetEntity::toDomain)  // Convert all entities to Pet objects
                .collect(Collectors.toList());  // Collect as a list (can be modified)
    }

    public void deleteById(Long id) {
        petJpaRepo.deleteById(id);
    }

   
}
