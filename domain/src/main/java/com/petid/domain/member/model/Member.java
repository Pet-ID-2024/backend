package com.petid.domain.member.model;

import com.petid.domain.type.Role;

public record Member(
        Long id,
        String uid,
        String platform,
        String fcmToken,
        String email,
        Role role
) {
    public Member updateFcmToken(
            String fcmToken
    ) {
        return new Member(
                id,
                uid,
                platform,
                fcmToken,
                email,
                role
        );
    }
}
