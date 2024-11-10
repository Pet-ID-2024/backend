package com.petid.api.member.dto;

import com.petid.domain.member.model.MemberAuthInfo;

public record MemberInfoDto() {
    public record Response(
            String name,
            String address,
            String addressDetails,
            String phone,
            String image,
            Long petId
    ) {
        public static Response from(MemberAuthInfo memberAuthInfo) {
            return new Response(
                    memberAuthInfo.name(),
                    memberAuthInfo.address(),
                    memberAuthInfo.addressDetails(),
                    memberAuthInfo.phone(),
                    memberAuthInfo.image(),
                    memberAuthInfo.petId()
            );
        }
    }
}
