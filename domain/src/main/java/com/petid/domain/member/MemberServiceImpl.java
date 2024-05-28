package com.petid.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberAuthManager memberAuthManager;

    @Override
    public boolean isMemberAuthed(String uid) {
        return memberAuthManager.existsByUid(uid);
    }
}
