package com.petid.api.member.dto;

import com.petid.domain.member.model.MemberAuth;

public record MemberAuthDto() {
    public record Request(
            String name,
            String address,
            String addressDetails,
            String phone
    ) {
        public MemberAuth toDomain(long memberId) {
            return new MemberAuth(
                    null,
                    memberId,
                    name,
                    null,
                    address,
                    addressDetails,
                    phone
            );
        }
    }

    public record Response(
            String name,
            String address,
            String addressDetails,
            String phone,
            String image
    ) {
        public static Response from(MemberAuth memberAuth) {
            return new Response(
                    memberAuth.name(),
                    memberAuth.address(),
                    memberAuth.addressDetails(),
                    memberAuth.phone(),
                    memberAuth.image()
            );
        }
    }
}
