package com.petid.domain.member.manager;

import com.petid.domain.exception.MemberAuthNotFoundException;
import com.petid.domain.member.model.MemberAuthInfo;
import com.petid.domain.member.repository.MemberAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberAuthManager {

    private final MemberAuthRepository memberAuthRepository;

    public MemberAuthInfo getByMemberId(
            long memberId
    ) {
        return memberAuthRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberAuthNotFoundException(memberId));
    }
}
