package com.petid.infra.pet.repository;

import com.petid.infra.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetImageJpaRepository extends JpaRepository<MemberEntity, Long> {
    
}
