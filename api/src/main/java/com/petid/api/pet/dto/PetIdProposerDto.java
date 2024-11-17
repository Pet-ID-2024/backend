package com.petid.api.pet.dto;

import com.petid.domain.member.model.MemberAuthInfo;

public record PetIdProposerDto(
        String name,
        String address,
        String addressDetails,
        String phone
) {
    public MemberAuthInfo toDomain(long memberId) {
        return new MemberAuthInfo(
                null,
                memberId,
                name,
                null,
                address,
                addressDetails,
                phone,
                null
        );
    }
}
