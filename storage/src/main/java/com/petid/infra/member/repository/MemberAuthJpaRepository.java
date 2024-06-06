package com.petid.infra.member.repository;

import com.petid.infra.member.entity.MemberAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberAuthJpaRepository extends JpaRepository<MemberAuthEntity, Long> {
    Optional<MemberAuthEntity> findByMemberId(Long id);
}
