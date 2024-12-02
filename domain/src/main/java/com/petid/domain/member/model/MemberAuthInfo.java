package com.petid.domain.member.model;

public record MemberAuthInfo(
        Long id,
        Long memberId,
        String name,
        String image,
        String address,
        String addressDetails,
        String rra, // Resident Registration Act
        String rraDetails,
        String phone,
        Long petId
) {
    public static MemberAuthInfo createDefaultMemberAuth(
            long memberId,
            String randomName
    ) {
        return new MemberAuthInfo(
                null,
                memberId,
                randomName,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public MemberAuthInfo update(
            MemberAuthInfo updateMemberAuthInfo
    ) {
        return new MemberAuthInfo(
                id,
                memberId,
                (updateMemberAuthInfo.name == null)  ? name : updateMemberAuthInfo.name,
                image,
                (updateMemberAuthInfo.address() == null) ? address : updateMemberAuthInfo.address,
                (updateMemberAuthInfo.addressDetails() == null) ? addressDetails : updateMemberAuthInfo.addressDetails,
                rra,
                rraDetails,
                (updateMemberAuthInfo.phone() == null) ? phone : updateMemberAuthInfo.phone,
                petId
        );
    }

    public boolean isAuthed() {
        return address != null && phone != null;
    }

    public MemberAuthInfo updateProfileImage(
            String filePath
    ) {
        return new MemberAuthInfo(
                id,
                memberId,
                name,
                filePath,
                address,
                addressDetails,
                rra,
                rraDetails,
                phone,
                petId
        );
    }
}
