package com.petid.domain.member.service;

import com.petid.domain.member.manager.MemberAuthManager;
import com.petid.domain.member.model.Member;
import com.petid.domain.member.model.MemberAuth;
import com.petid.domain.member.repository.MemberAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberAuthManager memberAuthManager;
    private final MemberAuthRepository memberAuthRepository;

    @Override
    public boolean isMemberAuthed(String uid) {
        return memberAuthManager.existsByUid(uid);
    }

    @Override
    public MemberAuth saveMemberAuth(
            Member member,
            MemberAuth memberAuth
    ) {
        return memberAuthRepository.save(member, memberAuth);
    }
}
