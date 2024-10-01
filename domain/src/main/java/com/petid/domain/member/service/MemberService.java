package com.petid.domain.member.service;

import com.petid.domain.member.manager.MemberAuthManager;
import com.petid.domain.member.manager.MemberManager;
import com.petid.domain.member.manager.MemberPolicyManager;
import com.petid.domain.member.model.Member;
import com.petid.domain.member.model.MemberAuth;
import com.petid.domain.member.model.MemberPolicy;
import com.petid.domain.member.repository.MemberAuthRepository;
import com.petid.domain.member.repository.MemberPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberManager memberManager;
    private final MemberAuthManager memberAuthManager;
    private final MemberAuthRepository memberAuthRepository;
    private final MemberPolicyManager memberPolicyManager;
    private final MemberPolicyRepository memberPolicyRepository;

    public boolean isMemberAuthed(
            long memberId
    ) {
        return memberAuthManager.existsByMemberId(memberId);
    }

    public MemberAuth updateMemberAuth(
            MemberAuth updateMemberAuth
    ) {
        MemberAuth memberAuth = memberAuthManager.getByMemberId(updateMemberAuth.memberId());
        MemberAuth updated = memberAuth.update(updateMemberAuth);

        return memberAuthRepository.save(updated);
    }

    public void updateOptionalPolicy(
            long memberId,
            boolean ad
    ) {
        Member member = memberManager.get(memberId);

        MemberPolicy memberPolicy = memberPolicyManager.getByMemberId(member.id());
        MemberPolicy updated = memberPolicy.updatePolicy(ad);

        memberPolicyRepository.save(updated);
    }
}
