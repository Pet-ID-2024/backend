package com.petid.domain.member.model;

public record MemberAuth(
        Long id,
        Long memberId,
        String name,
        String image,
        String address,
        String phone
) {
    public static MemberAuth createDefaultMemberAuth(
            long memberId,
            String randomName
    ) {
        return new MemberAuth(
                null,
                memberId,
                randomName,
                null,
                null,
                null
        );
    }

    public MemberAuth update(
            MemberAuth updateMemberAuth
    ) {
        return new MemberAuth(
                id,
                memberId,
                updateMemberAuth.name(),
                updateMemberAuth.image(),
                updateMemberAuth.address(),
                updateMemberAuth.phone()
        );
    }

    public boolean isAuthed() {
        return address != null && phone != null;
    }

    public MemberAuth updateProfileImage(
            String filePath
    ) {
        return new MemberAuth(
                id,
                memberId,
                name,
                filePath,
                address,
                phone
        );
    }
}
