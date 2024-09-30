package com.petid.domain.member.model;

public record MemberAuth(
        Long id,
        Long memberId,
        String name,
        String ssn,
        String address,
        String phone
) {
    public MemberAuth update(
            MemberAuth updateMemberAuth
    ) {
        return new MemberAuth(
                id,
                memberId,
                updateMemberAuth.name(),
                updateMemberAuth.ssn(),
                updateMemberAuth.address(),
                updateMemberAuth.phone()
        );
    }
}
