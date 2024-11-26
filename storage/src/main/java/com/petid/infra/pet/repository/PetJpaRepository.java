package com.petid.infra.pet.repository;

import com.petid.infra.pet.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetJpaRepository extends JpaRepository<PetEntity, Long> {
    Optional<PetEntity> findByOwnerId(long ownerId);
}
