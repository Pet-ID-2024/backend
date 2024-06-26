package com.petid.domain.member.model;

import com.petid.domain.type.Role;

public record Member(
        Long id,
        String uid,
        String platform,
        String email,
        Role role
) {
}
