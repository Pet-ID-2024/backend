package com.petid.infra.member.repository;

import com.petid.infra.member.entity.MemberPolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberPolicyJpaRepository extends JpaRepository<MemberPolicyEntity, Long> {
    Optional<MemberPolicyEntity> findByMemberId(Long memberId);
}
