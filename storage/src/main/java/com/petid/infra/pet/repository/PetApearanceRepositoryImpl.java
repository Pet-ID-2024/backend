package com.petid.infra.pet.repository;

import com.petid.domain.pet.entity.PetAppearance;
import com.petid.domain.pet.repository.PetAppearanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PetApearanceRepositoryImpl implements PetAppearanceRepository {
    private final PetAppearanceJpaRepository petAppearanceJpaRepo;

	@Override
	public Optional<PetAppearance> findByPetAppearanceId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public PetAppearance save(PetAppearance pet) {
		// TODO Auto-generated method stub
		return null;
	}

   
}
