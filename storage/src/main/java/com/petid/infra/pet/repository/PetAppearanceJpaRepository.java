package com.petid.infra.pet.repository;

import com.petid.infra.member.entity.MemberAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetAppearanceJpaRepository extends JpaRepository<MemberAuthEntity, Long> {    
}
