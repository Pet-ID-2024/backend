package com.petid.infra.pet.repository;

import com.petid.domain.pet.model.PetImage;
import com.petid.domain.pet.repository.PetImageRepository;
import com.petid.infra.pet.entity.PetImageEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PetImageRepositoryImpl implements PetImageRepository {

    private final PetImageJpaRepository petImageJpaRepo;

	 @Override
    @Transactional
    public PetImage createPetImage(Long petId, PetImage petImage) {
        PetImageEntity petImageEntity = PetImageEntity.from(petImage);
        petImageEntity.setPetId(petId);
        PetImageEntity savedPetImageEntity = petImageJpaRepo.save(petImageEntity);
        return savedPetImageEntity.toDomain();
    }

    @Override
    @Transactional
    public PetImage updatePetImage(PetImage petImage) {
        Optional<PetImageEntity> optionalPetImageEntity = petImageJpaRepo.findById(petImage.petImageId());
        if (optionalPetImageEntity.isPresent()) {
            PetImageEntity petImageEntity = optionalPetImageEntity.get();
            PetImageEntity updatedPetImageEntity = PetImageEntity.from(petImage);
            updatedPetImageEntity.setImagePath(petImageEntity.getImagePath());
            petImageJpaRepo.save(updatedPetImageEntity);
            return updatedPetImageEntity.toDomain();
        }
        throw new RuntimeException("Pet Image not found");
    }

    @Override
    @Transactional
    public void deletePetImageByPetId(Long petId) {
        petImageJpaRepo.deleteByPetId(petId);
    }

    @Override
    public Optional<PetImage> findPetImageById(Long petImageId) {
        return petImageJpaRepo.findById(petImageId).map(PetImageEntity::toDomain);
    }

    @Override
    public List<PetImage> findAllPetImages() {
        return petImageJpaRepo.findAll().stream().map(PetImageEntity::toDomain).collect(Collectors.toList());
    }

	@Override
	public void deletePetImage(Long petImageId) {
		petImageJpaRepo.deleteById(petImageId);
	}
}
