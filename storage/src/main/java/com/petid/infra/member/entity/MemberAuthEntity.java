package com.petid.infra.member.entity;

import com.petid.domain.member.model.MemberAuthInfo;
import com.petid.infra.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Table(name = "member_auth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberAuthEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;
    private String name;
    private String image;
    private String address;
    private String addressDetails;
    private String rra;
    private String rraDetails;
    private String phone;

    public static MemberAuthEntity from(
            MemberAuthInfo memberAuthInfo
    ) {
        return new MemberAuthEntity(
                memberAuthInfo.id(),
                memberAuthInfo.memberId(),
                memberAuthInfo.name(),
                memberAuthInfo.image(),
                memberAuthInfo.address(),
                memberAuthInfo.addressDetails(),
                memberAuthInfo.rra(),
                memberAuthInfo.rraDetails(),
                memberAuthInfo.phone()
        );
    }

    public MemberAuthInfo toDomain() {
        return new MemberAuthInfo(
                id,
                memberId,
                name,
                image,
                address,
                addressDetails,
                rra,
                rraDetails,
                phone,
                null
        );
    }
}
