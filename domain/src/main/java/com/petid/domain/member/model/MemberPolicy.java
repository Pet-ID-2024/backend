package com.petid.domain.member.model;

public record MemberPolicy(
        Long id,
        Long memberId,
        boolean advertisement
) {
    public static MemberPolicy of(
            Member member,
            boolean advertisement
    ) {
        return new MemberPolicy(
                null,
                member.id(),
                advertisement
        );
    }

    public MemberPolicy updatePolicy(
            boolean target
    ) {
        return new MemberPolicy(
                id,
                memberId,
                target
        );
    }
}
