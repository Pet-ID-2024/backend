package com.petid.api.member.dto;

import com.petid.domain.member.model.MemberAuth;

public record MemberAuthDto() {
    public record Request(
            String name,
            String address,
            String phone
    ) {
        public MemberAuth toDomain(long memberId) {
            return new MemberAuth(
                    null,
                    memberId,
                    name,
                    null,
                    address,
                    phone
            );
        }
    }

    public record Response(
            Long id,
            Long memberId,
            String name,
            String address,
            String phone
    ) {
        public static Response from(MemberAuth memberAuth) {
            return new Response(
                    memberAuth.id(),
                    memberAuth.memberId(),
                    memberAuth.name(),
                    memberAuth.address(),
                    memberAuth.phone()
            );
        }
    }
}
