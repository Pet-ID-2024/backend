package com.petid.infra.member.entity;

import com.petid.domain.member.model.MemberAuth;
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
    private String phone;
    private String address;

    public static MemberAuthEntity from(
            MemberAuth memberAuth
    ) {
        return new MemberAuthEntity(
                memberAuth.id(),
                memberAuth.memberId(),
                memberAuth.name(),
                memberAuth.image(),
                memberAuth.phone(),
                memberAuth.address()
        );
    }

    public MemberAuth toDomain() {
        return new MemberAuth(
                id,
                memberId,
                name,
                image,
                phone,
                address
        );
    }
}
