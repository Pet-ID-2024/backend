package com.petid.domain.member.repository;

import com.petid.domain.member.model.MemberAuth;

import java.util.Optional;

public interface MemberAuthRepository {
    Optional<MemberAuth> findByMemberId(Long id);

    MemberAuth save(MemberAuth memberAuth);
}
