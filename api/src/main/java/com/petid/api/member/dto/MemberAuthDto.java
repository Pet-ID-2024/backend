package com.petid.api.member.dto;

import com.petid.domain.member.model.MemberAuth;

public record MemberAuthDto() {
    public record Request(
            String name,
            String ssn,
            String address,
            String phone
    ) {
        public MemberAuth toDomain(String uid) {
            return new MemberAuth(
                    null,
                    uid,
                    name,
                    ssn,
                    address,
                    phone
            );
        }
    }

    public record Response(
            Long id,
            String uid,
            String name,
            String ssn,
            String address,
            String phone
    ) {
        public static Response from(MemberAuth memberAuth) {
            return new Response(
                    memberAuth.id(),
                    memberAuth.uid(),
                    memberAuth.name(),
                    memberAuth.ssn(),
                    memberAuth.address(),
                    memberAuth.phone()
            );
        }
    }
}
