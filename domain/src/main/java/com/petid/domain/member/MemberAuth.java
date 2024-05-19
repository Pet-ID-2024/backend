package com.petid.domain.member;

public record MemberAuth(
        int memberId,
        String name,
        String ssn,
        String address,
        String phone
) {
}
