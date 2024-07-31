package com.petid.domain.member.manager;

import com.petid.domain.exception.MemberPolicyNotFoundException;
import com.petid.domain.member.model.MemberPolicy;
import com.petid.domain.member.repository.MemberPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPolicyManager {

    private final MemberPolicyRepository memberPolicyRepository;

    public MemberPolicy getByMemberId(
            Long memberId
    ) {
        return memberPolicyRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberPolicyNotFoundException(memberId));
    }
}
