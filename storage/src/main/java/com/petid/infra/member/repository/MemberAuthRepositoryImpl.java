package com.petid.infra.member.repository;

import com.petid.domain.member.model.MemberAuth;
import com.petid.domain.member.repository.MemberAuthRepository;
import com.petid.infra.member.entity.MemberAuthEntity;
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
            MemberAuth memberAuth
    ) {
        return memberAuthJpaRepository.save(MemberAuthEntity.from(memberAuth)).toDomain();
    }
}
