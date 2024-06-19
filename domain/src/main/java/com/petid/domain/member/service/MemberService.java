package com.petid.domain.member.service;

import com.petid.domain.member.manager.MemberAuthManager;
import com.petid.domain.member.manager.MemberManager;
import com.petid.domain.member.model.Member;
import com.petid.domain.member.model.MemberAuth;
import com.petid.domain.member.repository.MemberAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberManager memberManager;
    private final MemberAuthManager memberAuthManager;
    private final MemberAuthRepository memberAuthRepository;

    public boolean isMemberAuthed(String uid) {
        return memberAuthManager.existsByUid(uid);
    }

    public MemberAuth saveMemberAuth(
            MemberAuth memberAuth
    ) {
        Member member = memberManager.getByUid(memberAuth.uid());

        return memberAuthRepository.save(member, memberAuth);
    }
}
