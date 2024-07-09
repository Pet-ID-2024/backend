package com.petid.infra.pet.repository;


import com.petid.infra.pet.entity.PetAppearanceEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PetAppearanceJpaRepository extends JpaRepository<PetAppearanceEntity, Long> {
	void deleteByPetId(Long petId);	
}
