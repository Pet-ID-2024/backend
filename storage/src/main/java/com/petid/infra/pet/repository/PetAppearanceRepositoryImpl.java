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
public class PetAppearanceRepositoryImpl implements PetAppearanceRepository {
    private final PetAppearanceJpaRepository petAppearanceJpaRepo;

    @Override
    @Transactional
    public PetAppearance createPetAppearance(PetAppearance petAppearance) {
        PetAppearanceEntity petAppearanceEntity = PetAppearanceEntity.from(petAppearance);
        PetAppearanceEntity savedPetAppearanceEntity = petAppearanceJpaRepo.save(petAppearanceEntity);
        return savedPetAppearanceEntity.toDomain();
    }

    @Override
    @Transactional
    public PetAppearance updatePetAppearance(PetAppearance petAppearance) {
        if (petAppearanceJpaRepo.findById(petAppearance.appearanceId()).isPresent()) {
            PetAppearanceEntity updatedPetAppearanceEntity = PetAppearanceEntity.from(petAppearance);
            petAppearanceJpaRepo.save(updatedPetAppearanceEntity);
            return updatedPetAppearanceEntity.toDomain();
        }
        throw new RuntimeException("Pet Appearance not found");
    }

    @Override
    @Transactional
    public void deletePetAppearanceById(Long appearanceId) {
        petAppearanceJpaRepo.deleteById(appearanceId);
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
