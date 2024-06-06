package com.petid.domain.member.model;

public record MemberAuth(
        Long id,
        long memberId,
        String name,
        String ssn,
        String address,
        String phone
) {
}
