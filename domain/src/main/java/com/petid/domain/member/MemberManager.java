package com.petid.domain.member;

import com.petid.domain.exception.MemberNotFoundException;
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

    public Member getByUid(String uid) {
        return memberRepository.findByUid(uid)
                .orElseThrow(() -> new MemberNotFoundException(uid));
    }
}
