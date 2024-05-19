package com.petid.domain.member;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findByUid(String email);

    Member save(Member member);
}
