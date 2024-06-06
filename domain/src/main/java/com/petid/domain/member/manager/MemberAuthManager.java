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

    public boolean existsByUid(String uid) {
        Member member = memberManager.getByUid(uid);

        return memberAuthRepository.findByMemberId(member.id()).isPresent();
    }
}
