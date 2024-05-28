package com.petid.domain.member;

import java.util.Optional;

public interface MemberAuthRepository {
    Optional<MemberAuth> findByMemberId(Long id);
}
