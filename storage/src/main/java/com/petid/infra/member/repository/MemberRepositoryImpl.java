package com.petid.infra.member.repository;

import com.petid.domain.member.model.Member;
import com.petid.domain.member.repository.MemberRepository;
import com.petid.infra.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;
    
    private final QMemberRepository qMemberRepository;

    @Override
    public Optional<Member> findByUid(String uid) {
        return memberJpaRepository.findByUid(uid)
                .map(MemberEntity::toDomain);
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(MemberEntity.from(member)).toDomain();
    }

    @Override
    public Optional<Member> findById(long memberId) {
        return memberJpaRepository.findById(memberId)
                .map(MemberEntity::toDomain);
    }

	@Override
	public List<Member> findMembersWithoutPets() {		
		return qMemberRepository.findMembersWithoutPets();
	}
}
