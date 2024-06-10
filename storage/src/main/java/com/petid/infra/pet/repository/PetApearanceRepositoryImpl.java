package com.petid.infra.pet.repository;

import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.pet.repository.PetAppearanceRepository;
import com.petid.infra.pet.entity.PetAppearanceEntity;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PetApearanceRepositoryImpl implements PetAppearanceRepository {
    private final PetAppearanceJpaRepository petAppearanceJpaRepo;

	 @Override
    @Transactional
    public PetAppearance createPetAppearance(Long petId, PetAppearance petAppearance) {
        PetAppearanceEntity petAppearanceEntity = PetAppearanceEntity.from(petAppearance);
        petAppearanceEntity.setPetId(petId);
        PetAppearanceEntity savedPetAppearanceEntity = petAppearanceJpaRepo.save(petAppearanceEntity);
        return savedPetAppearanceEntity.toDomain();
    }

    @Override
    @Transactional
    public PetAppearance updatePetAppearance(PetAppearance petAppearance) {
        Optional<PetAppearanceEntity> optionalPetAppearanceEntity = petAppearanceJpaRepo.findById(petAppearance.appearanceId());
        if (optionalPetAppearanceEntity.isPresent()) {
            PetAppearanceEntity petAppearanceEntity = optionalPetAppearanceEntity.get();
            PetAppearanceEntity updatedPetAppearanceEntity = PetAppearanceEntity.from(petAppearance);
            updatedPetAppearanceEntity.setId(petAppearanceEntity.getId());
            petAppearanceJpaRepo.save(updatedPetAppearanceEntity);
            return updatedPetAppearanceEntity.toDomain();
        }
        throw new RuntimeException("Pet Appearance not found");
    }

    @Override
    @Transactional
    public void deletePetAppearanceByPetId(Long petId) {
        petAppearanceJpaRepo.deleteByPetId(petId);
    }

    @Override
    public Optional<PetAppearance> findPetAppearanceById(Long appearanceId) {
        return petAppearanceJpaRepo.findById(appearanceId).map(PetAppearanceEntity::toDomain);
    }

    @Override
    public List<PetAppearance> findAllPetAppearances() {
        return petAppearanceJpaRepo.findAll().stream().map(PetAppearanceEntity::toDomain).collect(Collectors.toList());
    }
   
}
