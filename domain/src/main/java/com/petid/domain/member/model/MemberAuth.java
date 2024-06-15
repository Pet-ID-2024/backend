package com.petid.domain.member.model;

public record MemberAuth(
        Long id,
        String uid,
        String name,
        String ssn,
        String address,
        String phone
) {
}
