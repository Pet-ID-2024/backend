package com.petid.domain.member.manager;

import com.petid.domain.exception.MemberAuthNotFoundException;
import com.petid.domain.member.model.MemberAuth;
import com.petid.domain.member.repository.MemberAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberAuthManager {

    private final MemberAuthRepository memberAuthRepository;

    public MemberAuth getByMemberId(
            long memberId
    ) {
        return memberAuthRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberAuthNotFoundException(memberId));
    }
}
