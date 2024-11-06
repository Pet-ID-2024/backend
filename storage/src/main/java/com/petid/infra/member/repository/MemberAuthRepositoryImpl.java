package com.petid.infra.member.repository;

import com.petid.domain.member.model.MemberAuthInfo;
import com.petid.domain.member.repository.MemberAuthRepository;
import com.petid.infra.member.entity.MemberAuthEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberAuthRepositoryImpl implements MemberAuthRepository {

    private final QMemberAuthRepository qMemberAuthRepository;
    private final MemberAuthJpaRepository memberAuthJpaRepository;

    @Override
    public Optional<MemberAuthInfo> findByMemberId(long id) {
        return qMemberAuthRepository.findMemberInfo(id);
    }

    @Override
    public MemberAuthInfo save(
            MemberAuthInfo memberAuthInfo
    ) {
        return memberAuthJpaRepository.save(MemberAuthEntity.from(memberAuthInfo)).toDomain();
    }
}
