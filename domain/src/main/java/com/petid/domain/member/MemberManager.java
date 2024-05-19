package com.petid.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberManager {
    private final MemberRepository memberRepository;

    public Member getOrSave(
            Member member
    ) {
        return memberRepository.findByUid(member.uid())
                .orElseGet(() -> memberRepository.save(member));
    }
}
