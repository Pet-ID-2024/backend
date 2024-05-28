package com.petid.domain.member;

public record MemberAuth(
        Long id,
        long memberId,
        String name,
        String ssn,
        String address,
        String phone
) {
}
