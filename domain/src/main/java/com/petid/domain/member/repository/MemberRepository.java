package com.petid.domain.member.repository;

import com.petid.domain.member.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findByUid(String uid);

    Member save(Member member);

    Optional<Member> findById(long memberId);
    
    List<Member> findMembersWithoutPets();
}
