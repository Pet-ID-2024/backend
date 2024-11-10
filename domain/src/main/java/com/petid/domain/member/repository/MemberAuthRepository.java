package com.petid.domain.member.repository;

import com.petid.domain.member.model.MemberAuthInfo;

import java.util.Optional;

public interface MemberAuthRepository {
    Optional<MemberAuthInfo> findByMemberId(long id);

    MemberAuthInfo save(MemberAuthInfo memberAuthInfo);
}
