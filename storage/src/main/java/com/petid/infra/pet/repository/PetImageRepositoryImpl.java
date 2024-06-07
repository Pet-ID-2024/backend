package com.petid.infra.pet.repository;

import com.petid.domain.pet.model.PetImage;
import com.petid.domain.pet.repository.PetImageRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PetImageRepositoryImpl implements PetImageRepository {

    private final PetImageJpaRepository petImageJpaRepo;

	@Override
	public PetImage save(Long petId, PetImage petImage) {
		// TODO Auto-generated method stub
		return null;
	}

    

	
}
