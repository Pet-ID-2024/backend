package com.petid.domain.member.service;

import com.petid.domain.member.model.Member;
import com.petid.domain.member.model.MemberAuth;

public interface MemberService {
    boolean isMemberAuthed(String uid);

    MemberAuth saveMemberAuth(Member member, MemberAuth domain);
}
