package com.petid.api.member.dto;

import com.petid.domain.member.model.MemberAuthInfo;

public record MemberAuthDto() {
    public record Request(
            String address,
            String addressDetails,
            String phone
    ) {
        public MemberAuthInfo toDomain(long memberId) {
            return new MemberAuthInfo(
                    null,
                    memberId,
                    null,
                    null,
                    address,
                    addressDetails,
                    null,
                    null,
                    phone,
                    null
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
        public static Response from(MemberAuthInfo memberAuthInfo) {
            return new Response(
                    memberAuthInfo.name(),
                    memberAuthInfo.address(),
                    memberAuthInfo.addressDetails(),
                    memberAuthInfo.phone(),
                    memberAuthInfo.image()
            );
        }
    }
}
