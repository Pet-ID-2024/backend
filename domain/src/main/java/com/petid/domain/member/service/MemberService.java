package com.petid.domain.member.service;

import com.petid.domain.member.manager.MemberAuthManager;
import com.petid.domain.member.manager.MemberManager;
import com.petid.domain.member.manager.MemberPolicyManager;
import com.petid.domain.member.model.Member;
import com.petid.domain.member.model.MemberAuthInfo;
import com.petid.domain.member.model.MemberPolicy;
import com.petid.domain.member.repository.MemberAuthRepository;
import com.petid.domain.member.repository.MemberPolicyRepository;
import com.petid.domain.member.repository.MemberRepository;
import com.petid.domain.type.WithdrawalStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberManager memberManager;
    private final MemberRepository memberRepository;
    private final MemberAuthManager memberAuthManager;
    private final MemberAuthRepository memberAuthRepository;
    private final MemberPolicyManager memberPolicyManager;
    private final MemberPolicyRepository memberPolicyRepository;

    public boolean isMemberAuthed(
            long memberId
    ) {
        MemberAuthInfo memberAuthInfo = memberAuthManager.getByMemberId(memberId);

        return memberAuthInfo.isAuthed();
    }

    public MemberAuthInfo updateMemberAuth(
            MemberAuthInfo updateMemberAuthInfo
    ) {
        MemberAuthInfo memberAuthInfo = memberAuthManager.getByMemberId(updateMemberAuthInfo.memberId());
        MemberAuthInfo updated = memberAuthInfo.update(updateMemberAuthInfo);

        return memberAuthRepository.save(updated);
    }

    public MemberPolicy updateOptionalPolicy(
            long memberId,
            boolean ad
    ) {
        MemberPolicy memberPolicy = memberPolicyManager.getByMemberId(memberId);
        MemberPolicy updated = memberPolicy.updatePolicy(ad);

        return memberPolicyRepository.save(updated);
    }

    public MemberAuthInfo updateMemberProfileImage(
            long memberId,
            String filePath
    ) {
        MemberAuthInfo memberAuthInfo = memberAuthManager.getByMemberId(memberId);
        MemberAuthInfo updated = memberAuthInfo.updateProfileImage(filePath);

        return memberAuthRepository.save(updated);
    }

    public void withdraw(
            long memberId
    ) {
        Member member = memberManager.get(memberId);

        if (member.status() == WithdrawalStatus.NORMAL) {
            memberRepository.save(member.withdraw());
        }
    }

    public void restore(
            long memberId
    ) {
        Member member = memberManager.get(memberId);

        if (member.status() == WithdrawalStatus.IN_PROGRESS) {
            memberRepository.save(member.restore());
        }
    }

    public boolean getMemberStatus(
            long memberId
    ) {
        Member member = memberManager.get(memberId);

        return member.status() == WithdrawalStatus.IN_PROGRESS;
    }
}
