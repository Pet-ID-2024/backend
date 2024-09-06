package com.petid.domain.member.manager;

import com.petid.domain.member.model.Member;
import com.petid.domain.member.repository.MemberAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberAuthManager {

    private final MemberManager memberManager;
    private final MemberAuthRepository memberAuthRepository;

    public boolean existsById(
            long memberId
    ) {
        Member member = memberManager.get(memberId);

        return memberAuthRepository.findByMemberId(member.id()).isPresent();
    }
}
