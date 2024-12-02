package com.petid.infra.member.repository;

import com.petid.domain.member.manager.MemberManager;
import com.petid.domain.member.model.Member;
import com.petid.domain.member.model.MemberPolicy;
import com.petid.domain.member.repository.MemberPolicyRepository;
import com.petid.infra.member.entity.MemberPolicyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberPolicyRepositoryImpl implements MemberPolicyRepository {

    private final MemberManager memberManager;
    private final MemberPolicyJpaRepository jpaRepository;

    @Override
    public MemberPolicy save(
            MemberPolicy memberPolicy
    ) {
        Member member = memberManager.get(memberPolicy.memberId());

        return jpaRepository.save(MemberPolicyEntity.from(memberPolicy, member)).toDomain();
    }

    @Override
    public Optional<MemberPolicy> findByMemberId(Long memberId) {
        return jpaRepository.findByMemberId(memberId)
                .map(MemberPolicyEntity::toDomain);
    }
}
