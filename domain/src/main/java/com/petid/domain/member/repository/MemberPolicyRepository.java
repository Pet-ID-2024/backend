package com.petid.domain.member.repository;

import com.petid.domain.member.model.MemberPolicy;

import java.util.Optional;

public interface MemberPolicyRepository {
    MemberPolicy save(MemberPolicy memberPolicy);

    Optional<MemberPolicy> findByMemberId(Long memberId);
}
