package com.petid.api.member.dto;

import com.petid.domain.member.model.MemberAuth;

public record MemberAuthRequest(
        String name,
        String ssn,
        String address,
        String phone
) {
    public MemberAuth toDomain(long memberId) {
        return new MemberAuth(
                null,
                memberId,
                name,
                ssn,
                address,
                phone
        );
    }
}
