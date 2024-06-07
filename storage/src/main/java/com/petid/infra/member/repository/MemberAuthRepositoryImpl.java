package com.petid.infra.member.repository;

import com.petid.domain.member.model.Member;
import com.petid.domain.member.model.MemberAuth;
import com.petid.domain.member.repository.MemberAuthRepository;
import com.petid.infra.member.entity.MemberAuthEntity;
import com.petid.infra.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberAuthRepositoryImpl implements MemberAuthRepository {

    private final MemberAuthJpaRepository memberAuthJpaRepository;

    @Override
    public Optional<MemberAuth> findByMemberId(Long id) {
        return memberAuthJpaRepository.findByMemberId(id)
                .map(MemberAuthEntity::toDomain);
    }

    @Override
    public MemberAuth save(
            Member member,
            MemberAuth memberAuth
    ) {
        MemberAuthEntity memberAuthEntity = new MemberAuthEntity(
                null,
                MemberEntity.from(member),
                memberAuth.name(),
                memberAuth.ssn(),
                memberAuth.phone(),
                memberAuth.address()
        );

        return memberAuthJpaRepository.save(memberAuthEntity).toDomain();
    }
}
