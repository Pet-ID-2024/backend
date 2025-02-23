package com.petid.domain.member.model;

import com.petid.domain.type.Role;
import com.petid.domain.type.WithdrawalStatus;

import java.time.LocalDate;

public record Member(
        Long id,
        String uid,
        String platform,
        String fcmToken,
        String email,
        Role role,
        WithdrawalStatus status,
        LocalDate statusUpdateDate
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
                role,
                status,
                statusUpdateDate
        );
    }

    public Member withdraw() {
        return new Member(
                id,
                uid,
                platform,
                fcmToken,
                email,
                role,
                WithdrawalStatus.IN_PROGRESS,
                LocalDate.now()
        );
    }

    public Member restore() {
        return new Member(
                id,
                uid,
                platform,
                fcmToken,
                email,
                role,
                WithdrawalStatus.NORMAL,
                LocalDate.now()
        );
    }
}
